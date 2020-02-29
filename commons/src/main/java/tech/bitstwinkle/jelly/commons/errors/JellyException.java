/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.errors;

import com.google.common.base.MoreObjects;

/**
 * 通用异常定义
 *
 * @author suuyoo.wg on 2020/2/29
 */
public class JellyException extends RuntimeException {

  private static final long serialVersionUID = -1892252481811383300L;

  /**
   * 错误信息
   */
  private JellyError error;

  public JellyException() {
  }

  public JellyException(JellyError error) {
    super(error.getMessage());
    this.error = error;
  }

  public JellyError getError() {
    return error;
  }

  public void setError(JellyError error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("error", error)
        .toString();
  }
}
