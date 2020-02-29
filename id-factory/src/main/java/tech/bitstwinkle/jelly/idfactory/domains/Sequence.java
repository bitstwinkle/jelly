/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory.domains;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public class Sequence implements Serializable {

  private static final long serialVersionUID = -4725767506120603166L;

  /**
   * 业务唯一码
   */
  private String bizCode;

  /**
   * 最小值
   */
  private long min;

  /**
   * 最大值: 当超过最大值，则从最小值重新开始
   */
  private long max;

  /**
   * 最大位数
   */
  private int digits;

  /**
   * 每个截断步长，即每次本地获取的长度
   */
  private long step;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("bizCode", bizCode)
        .add("min", min)
        .add("max", max)
        .add("step", step)
        .toString();
  }

  public String getBizCode() {
    return bizCode;
  }

  public void setBizCode(String bizCode) {
    this.bizCode = bizCode;
  }

  public long getMin() {
    return min;
  }

  public void setMin(long min) {
    this.min = min;
  }

  public long getMax() {
    return max;
  }

  public void setMax(long max) {
    this.max = max;
    String maxStr = String.valueOf(max);
    this.digits = maxStr.length();
  }

  public long getStep() {
    return step;
  }

  public void setStep(long step) {
    this.step = step;
  }

  public int getDigits() {
    return digits;
  }

}
