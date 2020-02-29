/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.dal.converters;

import com.alibaba.fastjson.JSONObject;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author suuyoo.wg on 2020/2/29
 */
public class JpaJsonConverter implements AttributeConverter<JSONObject, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(JpaJsonConverter.class);

  @Override
  public String convertToDatabaseColumn(JSONObject attribute) {
    if (attribute == null) {
      return null;
    }
    try {
      return attribute.toJSONString();
    } catch (Exception e) {
      LOGGER.warn("convert attr to db value failed: {}", attribute.getClass().getName());
      return null;
    }
  }

  @Override
  public JSONObject convertToEntityAttribute(String dbData) {
    if (StringUtils.isEmpty(dbData)) {
      return null;
    }
    try {
      return JSONObject.parseObject(dbData);
    } catch (Exception e) {
      LOGGER.warn("convert db value to attr failed: {}", dbData);
      return null;
    }
  }
}
