/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bitstwinkle.jelly.idfactory.dal.SequenceEntity;
import tech.bitstwinkle.jelly.idfactory.dal.SequenceEntityRepository;
import tech.bitstwinkle.jelly.idfactory.domains.Section;
import tech.bitstwinkle.jelly.idfactory.domains.Sequence;
import tech.bitstwinkle.jelly.platform.retry.RetryHelper;
import tech.bitstwinkle.jelly.platform.tx.JellyTxTemplate;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public class LocalSequence {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalSequence.class);

  private static final Retryer<Boolean> RETRYER = RetryHelper.getNormalRetryer();
  private final Sequence sequence;
  /**
   * 序列数据操作对象
   */
  private final SequenceEntityRepository repository;
  /**
   * 事务模板
   */
  private final JellyTxTemplate jellyTx;
  private Lock lock = new ReentrantLock();
  /**
   * 本地增长SEQUENCE
   */
  private AtomicLong localSeq = new AtomicLong();
  /**
   * 本地截面
   */
  private Section section;

  public LocalSequence(Sequence sequence, SequenceEntityRepository repository,
      JellyTxTemplate jellyTx) {
    this.sequence = sequence;
    this.repository = repository;
    this.jellyTx = jellyTx;
  }

  /**
   * 获取下一个序列号
   *
   * @return 下一个序列号
   */
  public long nextSequence() {
    if (checkWhetherNeedRefresh()) {
      lock.lock();
      try {
        if (checkWhetherNeedRefresh()) {
          RETRYER.call(() -> doRefreshLocalSection());
        }
      } catch (ExecutionException e) {
        LOGGER.error("Retry Execution Exception", e);
      } catch (RetryException e) {
        LOGGER.error("Retry Retry Exception", e);
      } finally {
        lock.unlock();
      }
    }

    return localSeq.getAndIncrement();
  }

  /**
   * 左侧补零
   *
   * @param seq 序列
   * @return 补零后的字符串
   */
  public String formatSequence(long seq) {
    return String.format("%04d", seq);
  }

  /**
   * 该bizCode对应的Sequence进行初始化
   */
  private SequenceEntity doInit() {
    LOGGER.info("init sequence: {}", sequence);

    SequenceEntity sequenceEntity = new SequenceEntity();
    sequenceEntity.setBizCode(sequence.getBizCode());
    sequenceEntity.setMin(sequence.getMin());
    sequenceEntity.setMax(sequence.getMax());
    sequenceEntity.setStep(sequence.getStep());
    sequenceEntity.setCurrent(sequence.getMax());

    jellyTx.execute(status -> {
      repository.saveAndFlush(sequenceEntity);
      return true;
    });

    return sequenceEntity;
  }

  /**
   * 更新本地截面
   *
   * @return ture: 成功; false: 失败
   */
  private boolean doRefreshLocalSection() {
    LOGGER.info("refresh local section: " + section);

    SequenceEntity sequenceEntity;
    Optional<SequenceEntity> optional = repository.findById(sequence.getBizCode());
    sequenceEntity = optional.orElseGet(this::doInit);

    sequence.setMin(sequenceEntity.getMin());
    sequence.setMax(sequenceEntity.getMax());
    sequence.setStep(sequenceEntity.getStep());

    long current = sequenceEntity.getCurrent();

    long start = current;

    /**
     * 如果开始值大于最大值，则需要将Sequence重新开始
     */
    if (start > sequence.getMax()) {
      LOGGER.info("{} > {}, need restart", start, sequence.getMax());

      sequenceEntity.setCurrent(sequence.getMin());

      jellyTx.execute(status -> {
        repository.saveAndFlush(sequenceEntity);
        return true;
      });
      return false;
    }

    long end = current + sequence.getStep();
    if (end > sequence.getMax()) {
      end = sequence.getMax();
    }

    long dbNewCurrent = end + 1;
    if (dbNewCurrent > sequence.getMax()) {
      dbNewCurrent = sequence.getMin();
    }
    sequenceEntity.setCurrent(dbNewCurrent);

    jellyTx.execute(status -> {
      repository.saveAndFlush(sequenceEntity);
      return true;
    });

    this.section = new Section(start, end);
    this.localSeq.set(this.section.getStart());

    LOGGER.info("current: {}|{}", section, localSeq);

    return true;
  }

  /**
   * 判断是否需要更新本地Setion
   *
   * @return
   */
  private boolean checkWhetherNeedRefresh() {
    return section == null || localSeq.get() > section.getEnd();
  }

}
