/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.mq;

/**
 * @author suuyoo.wg on 2019/10/8
 */
public enum MessageConsumeResultEnum {

  /**
   * 消费成功
   */
  SUCCESS,

  /**
   * 消费失败，需要重新发送
   */
  FAILED,

  /**
   * 忽略该消息
   */
  IGNORE

}
