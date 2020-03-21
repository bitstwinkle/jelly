/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public final class MoneyHelper {

  /**
   * 默认币种
   */
  public static String DEFAULT_CURRENCY = "CNY";

  /**
   * 金额0
   */
  public static BigDecimal ZERO = new BigDecimal(0L);

  /**
   * 判断是否有效的币种类型
   *
   * @param currencyCode 币种码
   * @return 是否正确币种类型
   */
  public static boolean isValidCurrency(String currencyCode) {
    try {
      Currency.getInstance(currencyCode);
      return true;
    } catch (IllegalArgumentException iae) {
      return false;
    }
  }


  /**
   * 判断valA是否大于valB，如果valA大于valB，那么返回true，否则返回false
   *
   * @param valA A值
   * @param valB B值
   * @return A值是否大于B值
   */
  public static boolean greaterThan(BigDecimal valA, BigDecimal valB) {
    return (valA.compareTo(valB) > 0);
  }

  /**
   * 判断valA和valB的值是否相等，如果valA和valB的值是否相等，那么返回true，否则返回false
   *
   * @param valA A值
   * @param valB B值
   * @return A值是否等于B值
   */
  public static boolean equals(BigDecimal valA, BigDecimal valB) {
    return (valA.compareTo(valB) == 0);
  }

  /**
   * 用于货币计算的加法，返回结果默认精确到小数点后4位，舍入模式：四舍五入
   *
   * @param valA A值
   * @param valB B值
   * @return （valA + valB）的结果
   */
  public static BigDecimal add(BigDecimal valA, BigDecimal valB) {
    return valA.add(valB).setScale(4, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的加法，返回结果的舍入模式：四舍五入
   *
   * @param valA  A值
   * @param valB  B值
   * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
   * @return （valA + valB）的结果
   */
  public static BigDecimal add(BigDecimal valA, BigDecimal valB, int scale) {
    return valA.add(valB).setScale(scale, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的减法，返回结果默认精确到小数点后4位
   *
   * @param valA A值
   * @param valB B值
   * @return （valA - valB）的结果
   */
  public static BigDecimal minus(BigDecimal valA, BigDecimal valB) {
    return valA.add(valB.negate()).setScale(4, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的减法，返回结果的舍入模式：四舍五入
   *
   * @param valA A值
   * @param valB B值
   * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
   * @return （valA - valB）的结果
   */
  public static BigDecimal minus(BigDecimal valA, BigDecimal valB, int scale) {
    return valA.add(valB.negate()).setScale(scale, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的乘法，返回结果默认精确到小数点后4位
   *
   * @param valA A值
   * @param valB B值
   * @return （valA * valB）的结果
   */
  public static BigDecimal multiply(BigDecimal valA, BigDecimal valB) {
    return valA.multiply(valB).setScale(4, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的乘法，返回结果的舍入模式：四舍五入
   *
   * @param valA A值
   * @param valB B值
   * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
   * @return （valA * valB）的结果
   */
  public static BigDecimal multiply(BigDecimal valA, BigDecimal valB, int scale) {
    return valA.multiply(valB).setScale(scale, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的除法，返回结果默认精确到小数点后4位
   *
   * @param valA 被除数
   * @param valB 除数
   * @return （valA / valB）的结果
   */
  public static BigDecimal divide(BigDecimal valA, BigDecimal valB) {
    if (BigDecimal.ZERO.compareTo(valB) == 0) {
      throw new ArithmeticException("除数不能为0");
    }
    return valA.divide(valB, 4, RoundingMode.HALF_UP);
  }

  /**
   * 用于货币计算的除法，返回结果的舍入模式：四舍五入
   *
   * @param valA  被除数
   * @param valB  除数
   * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
   * @return （valA / valB）的结果
   */
  public static BigDecimal divide(BigDecimal valA, BigDecimal valB, int scale) {
    if (BigDecimal.ZERO.compareTo(valB) == 0) {
      throw new ArithmeticException("除数不能为0");
    }
    return valA.divide(valB, scale, RoundingMode.HALF_UP);
  }

  /**
   * 将指定的值转换为BigDecimal对象，如果val为null或者为空，那么默认返回0
   *
   * @param val 值字符串
   * @return 值
   */
  public static BigDecimal toBigDecimal(String val) {
    if (val == null || "".equals(val.trim())) {
      return BigDecimal.ZERO;
    } else {
      return new BigDecimal(val);
    }
  }

  /**
   * 元转分
   *
   * @param bigDecimal 源值
   * @return 分值
   */
  public static long toCent(BigDecimal bigDecimal) {
    if (bigDecimal == null) {
      throw new JellyException(SystemErrorEnum.NPE_ERROR.getError("bigDecimal"));
    }
    return multiply(bigDecimal, new BigDecimal(100)).longValue();
  }
}
