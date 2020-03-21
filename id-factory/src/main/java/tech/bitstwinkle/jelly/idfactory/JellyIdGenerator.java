/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public interface JellyIdGenerator {

  int NO_DB_FLAG = -1;

  int MIN_DB_FLAG = 0;

  int MAX_DB_FLAG = 99;

  /**
   * 生成ID
   *
   * @return ID
   */
  String generateId();

  /**
   * 指定分库分别位生成ID
   *
   * @param dbFlag db标识
   * @return ID
   */
  String generateId(int dbFlag);

}
