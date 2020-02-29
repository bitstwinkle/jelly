/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.errors.enums;

import tech.bitstwinkle.jelly.commons.errors.JellyError;
import tech.bitstwinkle.jelly.commons.errors.JellyErrorGetter;
import tech.bitstwinkle.jelly.commons.errors.JellyErrorTemplate;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public enum SystemErrorEnum implements JellyErrorGetter {

  /**
   * 系统错误
   */
  SYSTEM_ERROR("SYSTEM_ERROR", "System Error.", "系统繁忙"),

  /**
   * 带消息的系统错误
   */
  SYSTEM_ERROR_WITH_MSG("SYSTEM_ERROR", "System Error: {0}", "系统繁忙: {1}"),

  /**
   * 不应该为NULL| 需要提供1个参数（参数名）
   */
  NPE_ERROR("NPE_ERROR", "NPE: {0}", "空指针: {0}"),
  ;

  private JellyErrorTemplate template;

  SystemErrorEnum(String code, String message, String view) {
    this.template = new JellyErrorTemplate(code, message, view);
  }

  @Override
  public JellyError getError(Object... args) {
    return this.template.getError(args);
  }

}
