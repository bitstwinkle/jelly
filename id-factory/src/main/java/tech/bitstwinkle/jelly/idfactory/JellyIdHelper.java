/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.idfactory;

import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;

/**
 * @author suuyoo.wg on 2020/3/1
 */
public final class JellyIdHelper {

  static String generateWithFlag(String core) {
    int len = core.length();
    char first = core.charAt(0);
    char end = core.charAt(len - 1);
    int flag = len * len + first * first + end * end + len * (first + end);
    flag = flag % 10;
    return core + flag;
  }

  /**
   * 判定是否是有效ID
   *
   * @param id
   * @return
   */
  public static boolean isValidId(String id) {
    if (id == null || id.isEmpty()) {
      return false;
    }
    String core = id.substring(0, id.length() - 1);
    String genId = generateWithFlag(core);
    return id.endsWith(genId);
  }

  public static void checkDbFlag(int dbFlag) {
    if (dbFlag < JellyIdGenerator.MIN_DB_FLAG || dbFlag > JellyIdGenerator.MAX_DB_FLAG) {
      throw new JellyException(
          SystemErrorEnum.SYSTEM_ERROR_WITH_MSG.getError("invalid db-flag:" + dbFlag));
    }
  }

  /**
   * 获取某个ID的分库分表位:倒数2-3位为分库分表位
   *
   * @param id
   * @return
   */
  public static int getDbFlag(String id) {
    int len = id.length();
    int beginPos = len - 3;
    int endPos = len - 2;
    String dbFlagStr = id.substring(beginPos, endPos);
    return Integer.valueOf(dbFlagStr);
  }

}
