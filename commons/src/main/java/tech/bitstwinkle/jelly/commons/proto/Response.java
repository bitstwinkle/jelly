/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.proto;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import tech.bitstwinkle.jelly.commons.errors.JellyError;
import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public class Response<T> implements Serializable {

  private static final long serialVersionUID = -4989969116461765906L;

  private boolean success;
  private JellyError error;
  private T data;

  public Response() {
  }

  public Response(JellyError error) {
    this.success = false;
    this.error = error;
  }

  public Response(T data) {
    this.success = true;
    this.data = data;
  }

  /**
   * 判定响应正确
   *
   * @param response
   */
  public static void assertSuccess(Response response) {
    if (response == null) {
      throw new JellyException(SystemErrorEnum.NPE_ERROR.getError("response"));
    }

    if (!response.isSuccess()) {
      throw new JellyException(response.getError());
    }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("success", success)
        .add("error", error)
        .add("data", data)
        .toString();
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public JellyError getError() {
    return error;
  }

  public void setError(JellyError error) {
    this.error = error;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
