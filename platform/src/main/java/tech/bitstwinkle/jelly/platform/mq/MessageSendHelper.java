package tech.bitstwinkle.jelly.platform.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;

/**
 * @author suuyoo.wg on 2019/10/26
 */
public class MessageSendHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger("WATT-MQ-DIGEST");

  public static String send(ProducerBean producer, Message message) {
    SendResult sendResult;
    try {
      sendResult = producer.send(message);
    } catch (ONSClientException e) {
      LOGGER.info("[{}]{},{} [NG]", message.getMsgID(), message.getTopic(), message.getTag());
      LoggerFactory.getLogger(MessageSendHelper.class).error("send failed.", e);
      throw new JellyException(SystemErrorEnum.SYSTEM_ERROR_WITH_MSG.getError(e.getMessage()));
    }
    LOGGER.info("[{}]{},{} [OK]", sendResult.getMessageId(), message.getTopic(), message.getTag());
    return sendResult.getMessageId();
  }


}
