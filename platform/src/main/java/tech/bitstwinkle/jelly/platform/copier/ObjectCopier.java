/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.copier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public class ObjectCopier {

  private static Lock lock = new ReentrantLock();

  private static Map<String, BeanCopier> beanCopierMap = new HashMap<>();

  private ObjectCopier() {

  }

  public static BeanCopier getBeanCopier(Class source, Class target) {

    String key = source.getName() + "->" + target.getName();
    BeanCopier beanCopier = beanCopierMap.get(key);
    if (beanCopier == null) {
      lock.lock();
      try {
        if (!beanCopierMap.containsKey(key)) {
          beanCopier = BeanCopier.create(source, target, false);
          beanCopierMap.put(key, beanCopier);
        }
      } finally {
        lock.unlock();
      }
    }
    return beanCopier;

  }

}
