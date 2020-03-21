/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.mq;

import com.alibaba.fastjson.JSONObject;

/**
 * 消息编解码
 *
 * @author suuyoo.wg on 2019/10/8
 */
public class MessageCodec {

  /**
   * 将java对象编码成字节
   *
   * @param obj 要编码的信息
   * @return 编码后的字节
   */
  public static byte[] encode(Object obj) {
    byte[] payload = JSONObject.toJSONBytes(obj);
    return payload;
  }

  /**
   * 将字节解码成java对象
   *
   * @param payload 携带的信息
   * @param clazz   原型类
   * @param <T>     解码后的对象
   * @return 解码后的对象
   */
  public static <T> T decode(byte[] payload, Class clazz) {
    T obj = JSONObject.parseObject(payload, clazz);
    return obj;
  }
}
