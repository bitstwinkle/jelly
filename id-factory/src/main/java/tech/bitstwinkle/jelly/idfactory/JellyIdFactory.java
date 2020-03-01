/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.bitstwinkle.jelly.idfactory.dal.SequenceEntityRepository;
import tech.bitstwinkle.jelly.idfactory.domains.Sequence;
import tech.bitstwinkle.jelly.platform.tx.JellyTxTemplate;

/**
 * @author suuyoo.wg on 2020/3/1
 */
@Component
public class JellyIdFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(JellyIdFactory.class);

  @Autowired
  private SequenceEntityRepository sequenceEntityRepository;

  @Autowired
  private JellyTxTemplate jellyTx;

  /**
   * 创建ID生成器
   *
   * @param timestampTemplate
   * @param sequence
   * @return
   */
  public JellyIdGenerator createIdGenerator(String timestampTemplate, Sequence sequence) {

    LOGGER.info("create JellyIdGenerator: {}|{}", timestampTemplate, sequence);

    JellyIdGenerator wattIdGenerator = new LocalJellyIdGenerator(timestampTemplate, sequence,
        sequenceEntityRepository, jellyTx);

    return wattIdGenerator;
  }

}
