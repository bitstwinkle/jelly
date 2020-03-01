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
public class Meta implements Serializable {

  /**
   * 请求编号
   */
  private String requestId;

  /**
   * Trace编号
   */
  private String traceId;

  /**
   * 时间时间戳
   */
  private long dt;

  /**
   * 发起或者响应请求的节点应用名
   */
  private String node;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("requestId", requestId)
        .add("traceId", traceId)
        .add("dt", dt)
        .add("node", node)
        .toString();
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public long getDt() {
    return dt;
  }

  public void setDt(long dt) {
    this.dt = dt;
  }

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }
}
