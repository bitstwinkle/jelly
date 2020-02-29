/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.clock;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @author suuyoo.wg on 2020/2/29
 */
@Component
public class JellyClock {

  private TimeGetter timeGetter = TimeGetter.CURRENT;

  public Date now() {
    return new Date((timeGetter.getTime()));
  }

  public TimeGetter getTimeGetter() {
    return timeGetter;
  }

  public void setTimeGetter(TimeGetter timeGetter) {
    this.timeGetter = timeGetter;
  }

}
