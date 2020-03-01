/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.asserts;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.ParameterErrorEnum;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public final class RequestAssert {

  /**
   * 断言非空
   */
  public static void notNull(Object para, String name) throws JellyException {
    if (para == null) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_MISS_ERROR.getError(name));
    }
  }

  /**
   * 断言非空字符串
   */
  public static void notBlank(String para, String name) throws JellyException {
    if (StringUtils.isBlank(para)) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_MISS_ERROR.getError(name));
    }
  }

  /**
   * 断言集合不空
   *
   * @param col
   * @param name
   * @throws JellyException
   */
  public static void notEmpty(Collection<?> col, String name) throws JellyException {
    if (CollectionUtils.isEmpty(col)) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_MISS_ERROR.getError(name));
    }
  }

  /**
   * 断言集合不空
   *
   * @param map
   * @param name
   * @throws JellyException
   */
  public static void notEmpty(Map<?, ?> map, String name) throws JellyException {
    if (MapUtils.isEmpty(map)) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_MISS_ERROR.getError(name));
    }
  }

  /**
   * 判断数组不为空
   *
   * @param col
   * @param name
   * @throws JellyException
   */
  public static void notEmpty(Object[] col, String name) throws JellyException {
    if (col == null || col.length == 0) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_MISS_ERROR.getError(name));
    }
  }

  /**
   * 断言为真
   */
  public static void isTrue(boolean cond, String name) throws JellyException {
    if (!cond) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_INVALID_ERROR.getError(name));
    }
  }

  /**
   * 断言为手机
   *
   * @param input
   * @param errorMsg
   * @throws JellyException
   */
  public static void isMobile(String input, String errorMsg) throws JellyException {
    if (input.length() != 11) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_FORMAT_ERROR.getError(errorMsg));
    }

    String phoneRegex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[0,5,6])|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[1|3|8|9]))\\d{8}$";
    Pattern pattern = Pattern.compile(phoneRegex);
    if (!pattern.matcher(input).matches()) {
      throw new JellyException(ParameterErrorEnum.PARAMETER_FORMAT_ERROR.getError(errorMsg));
    }
  }

}
