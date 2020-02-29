/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.errors;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public interface JellyErrorGetter {

  /**
   * 根据参数构建错误信息
   *
   * @param args
   * @return
   */
  JellyError getError(Object... args);

}
