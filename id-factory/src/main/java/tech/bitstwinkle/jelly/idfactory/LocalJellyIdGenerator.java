/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bitstwinkle.jelly.idfactory.dal.SequenceEntityRepository;
import tech.bitstwinkle.jelly.idfactory.domains.Sequence;
import tech.bitstwinkle.jelly.platform.tx.JellyTxTemplate;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public class LocalJellyIdGenerator implements JellyIdGenerator {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalJellyIdGenerator.class);

  /**
   * 时间戳模板
   */
  private final DateTimeFormatter timestampTemplate;

  /**
   * SEQUENCE发生器
   */
  private final LocalSequence localSequence;

  /**
   * 序列数据操作对象
   */
  private final SequenceEntityRepository repository;

  /**
   * 事务模板
   */
  private final JellyTxTemplate jellyTx;

  LocalJellyIdGenerator(String timestampTemplate, Sequence sequence,
      SequenceEntityRepository repository,
      JellyTxTemplate jellyTx) {
    this.timestampTemplate = DateTimeFormatter.ofPattern(timestampTemplate);
    this.localSequence = new LocalSequence(sequence, repository, jellyTx);
    this.repository = repository;
    this.jellyTx = jellyTx;

    LOGGER.info("init LocalJellyIdGenerator: {}|{}", timestampTemplate, sequence);
  }

  @Override
  public String generateId() {
    String core = doGenerateCode(JellyIdGenerator.NO_DB_FLAG);
    return JellyIdHelper.generateWithFlag(core);
  }

  @Override
  public String generateId(int dbFlag) {
    JellyIdHelper.checkDbFlag(dbFlag);
    String core = doGenerateCode(dbFlag);
    return JellyIdHelper.generateWithFlag(core);
  }

  private String doGenerateCode(int dbFlag) {
    LocalDateTime localDateTime = LocalDateTime.now();
    String timestampStr = localDateTime.format(this.timestampTemplate);
    long seq = localSequence.nextSequence();
    String seqStr = localSequence.formatSequence(seq);

    if (dbFlag == JellyIdGenerator.NO_DB_FLAG) {
      return timestampStr + seqStr;
    }

    return timestampStr + seqStr + String.format("%02d", dbFlag);
  }

}
