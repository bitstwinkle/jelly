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
public class Section implements Serializable {

  private static final long serialVersionUID = -286021181352084427L;

  /**
   * 从哪个值开始
   */
  private final long start;

  /**
   * 到哪个值结束
   */
  private final long end;

  public Section(long start, long end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("start", start)
        .add("end", end)
        .toString();
  }

  public long getStart() {
    return start;
  }

  public long getEnd() {
    return end;
  }

}
