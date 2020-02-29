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
public enum ParameterErrorEnum implements JellyErrorGetter {

  /**
   * 缺少参数| 需要提供1个参数（参数名）
   */
  PARAMETER_MISS_ERROR("PARAMETER_MISS_ERROR", "miss parameter: {0}", "缺少参数: {0}"),

  /**
   * 无效参数错误|  需要提供1个参数（参数名）
   */
  PARAMETER_INVALID_ERROR("PARAMETER_INVALID_ERROR", "parameter {0} is invalid", "无效参数: {0}"),

  /**
   * 参数格式错误| 需要提供1个参数（参数名）
   */
  PARAMETER_FORMAT_ERROR("PARAMETER_FORMAT_ERROR", "parameter {0} format is error", "无效参数格式：{0}"),

  /**
   * 通用参数错误| 需要提供2个参数（message, view）
   */
  PARAMETER_ERROR("PARAMETER_ERROR", "{0}", "{1}");

  private JellyErrorTemplate template;

  ParameterErrorEnum(String code, String messageTpl, String viewTpl) {
    this.template = new JellyErrorTemplate(code, messageTpl, viewTpl);
  }

  @Override
  public JellyError getError(Object... args) {
    return this.template.getError(args);
  }

}
