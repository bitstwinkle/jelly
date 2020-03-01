/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.commons.ids;

import java.util.UUID;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public final class JellyIds {

  public static String uniqueId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

}
