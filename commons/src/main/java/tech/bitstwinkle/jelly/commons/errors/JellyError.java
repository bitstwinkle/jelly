/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.errors;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

/**
 * 通用的错误定义
 *
 * @author suuyoo.wg on 2020/2/29
 */
public class JellyError implements Serializable {

  private static final long serialVersionUID = 3015115873623521573L;

  /**
   * 错误码
   */
  private String code;

  /**
   * 错误描述
   */
  private String message;

  /**
   * 错误信息可读性描述
   */
  private String view;

  public JellyError() {
  }

  public JellyError(String code, String message, String view) {
    this.code = code;
    this.message = message;
    this.view = view;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("code", code)
        .add("message", message)
        .add("view", view)
        .toString();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getView() {
    if (this.view == null) {
      return this.getMessage();
    }
    return view;
  }

  public void setView(String view) {
    this.view = view;
  }
}
