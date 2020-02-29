/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory.dal;

import com.google.common.base.MoreObjects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tech.bitstwinkle.jelly.platform.dal.SelfPkEntity;

/**
 * @author suuyoo.wg on 2020/3/1
 */
@Entity
@Table(name = "bitstwinkle_jelly_sequence")
@EntityListeners(AuditingEntityListener.class)
public class SequenceEntity extends SelfPkEntity {

  private static final long serialVersionUID = -3270355354014935086L;

  /**
   * 业务唯一码
   */
  @Id
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
   * 当前值
   */
  private long current;

  /**
   * 每个截断步长，即每次本地获取的长度
   */
  private long step;

  @Version
  private long version;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("bizCode", bizCode)
        .add("min", min)
        .add("max", max)
        .add("current", current)
        .add("step", step)
        .add("version", version)
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
  }

  public long getCurrent() {
    return current;
  }

  public void setCurrent(long current) {
    this.current = current;
  }

  public long getStep() {
    return step;
  }

  public void setStep(long step) {
    this.step = step;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

}
