/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.errors;

import java.text.MessageFormat;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public class JellyErrorTemplate implements JellyErrorGetter {

  /**
   * 错误码
   */
  private String code;

  /**
   * 错误描述模板
   */
  private String messageTpl;

  /**
   * 错误信息可读性描述模板
   */
  private String viewTpl;

  public JellyErrorTemplate(String code, String messageTpl, String viewTpl) {
    this.code = code;
    this.messageTpl = messageTpl;
    this.viewTpl = viewTpl;
  }

  @Override
  public JellyError getError(Object... args) {

    String message;
    String view;

    try {
      message = MessageFormat.format(messageTpl, args);
      view = MessageFormat.format(viewTpl, args);
    } catch (Exception e) {
      return SystemErrorEnum.SYSTEM_ERROR.getError();
    }

    return new JellyError(this.code, message, view);
  }
}
