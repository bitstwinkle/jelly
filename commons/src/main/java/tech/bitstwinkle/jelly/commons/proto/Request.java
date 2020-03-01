/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.proto;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public class Request implements Serializable {

  private static final long serialVersionUID = 3336200324309927170L;

  private Meta meta;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("meta", meta)
        .toString();
  }

  public Meta getMeta() {
    return meta;
  }

  public void setMeta(Meta meta) {
    this.meta = meta;
  }
}
