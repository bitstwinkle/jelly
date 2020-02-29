/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.clock;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public interface TimeGetter {

  TimeGetter CURRENT = () -> System.currentTimeMillis();

  /**
   * 当前时间
   */
  long getTime();

}
