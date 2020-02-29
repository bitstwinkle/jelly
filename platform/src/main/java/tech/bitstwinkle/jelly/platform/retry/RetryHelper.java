/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public class RetryHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(RetryHelper.class);

  /**
   * 通用重试间隔毫秒数
   */
  private static final long NORMAL_INTERVAL = 10L;

  /**
   * 通用最大尝试次数
   */
  private static final int NORMAL_RETRY_TIMES = 10;

  private static final Retryer<Boolean> NORMAL_RETRYER = RetryerBuilder.<Boolean>newBuilder()
      .retryIfException()
      .retryIfResult(result -> {
        if (result == null) {
          return true;
        }
        return !result;
      })
      .withWaitStrategy(WaitStrategies.fixedWait(NORMAL_INTERVAL, TimeUnit.MILLISECONDS))
      .withStopStrategy(StopStrategies.stopAfterAttempt(NORMAL_RETRY_TIMES))
      .build();

  public static Retryer<Boolean> getNormalRetryer() {
    return NORMAL_RETRYER;
  }
}
