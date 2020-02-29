/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.tools.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import javax.crypto.spec.SecretKeySpec;

/**
 * 通用哈希算法
 *
 * @author suuyoo.wg on 2020/2/29
 */
public class HashHelper {

  /**
   * MD5加密
   *
   * @return
   */
  public static String md5(String str) {
    if (str == null) {
      return null;
    }
    return Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
  }

  /**
   * sha1加密
   *
   * @return
   */
  public static String sha1(String str) {
    if (str == null) {
      return null;
    }
    return Hashing.sha1().newHasher().putString(str, Charsets.UTF_8).hash().toString();
  }

  /**
   * sha512加密
   *
   * @return
   */
  public static String sha512(String str) {
    if (str == null) {
      return null;
    }
    return Hashing.sha512().newHasher().putString(str, Charsets.UTF_8).hash().toString();
  }

  public static String hmacSha256(String str, String key) throws Exception {
    if (str == null) {
      return null;
    }
    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(Charsets.UTF_8), "HmacSHA256");
    return Hashing.hmacSha256(secretKeySpec).newHasher().putString(str, Charsets.UTF_8).hash()
        .toString();
  }
}
