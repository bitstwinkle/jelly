package tech.bitstwinkle.jelly.platform.mq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * topic，下多个tag
 *
 * @author suuyoo.wg on 2019/10/8
 */
public abstract class BaseMessageListener<T> implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger("WATT-MQ-DIGEST");

    @Override
    public Action consume(Message message, ConsumeContext context) {
        LOGGER.info("[come]{}|{}|{}|{}", message.getTopic(), message.getTag(), message.getKey(),
            message.getMsgID());

        byte[] payload = message.getBody();

        T payloadObj;

        try {
            payloadObj = MessageCodec.decode(payload, this.getPayloadClass());
        } catch (Exception e) {
            LOGGER.warn("[decode failed]{}|{}|{}|{}", message.getTopic(), message.getTag(),
                message.getKey(), message.getMsgID());
            LoggerFactory.getLogger(this.getClass())
                .error("decode message:" + message.getMsgID() + " failed", e);
            return Action.ReconsumeLater;
        }

        Action action;
        MessageConsumeResultEnum result;

        try {
            result = onConsume(payloadObj);
            LOGGER.info("[consume]{}|{}|{}|{}--[{}]", message.getTopic(), message.getTag(),
                message.getKey(), message.getMsgID(), result.name());
            switch (result) {
                case FAILED:
                    action = Action.ReconsumeLater;
                    break;
                default:
                    action = Action.CommitMessage;
                    break;
            }
        } catch (Exception e) {
            LOGGER.warn("[consume exception]{}|{}|{}|{}", message.getTopic(), message.getTag(),
                message.getKey(), message.getMsgID());
            LoggerFactory.getLogger(this.getClass())
                .error("consume message:" + message.getMsgID() + " failed", e);
            action = Action.ReconsumeLater;
        }

        return action;
    }

    /**
     * 获取该监听器的payload主体类型
     *
     * @return 携带信息的类原型
     */
    public abstract Class<T> getPayloadClass();

    /**
     * 执行消息消费
     *
     * @param payload 携带信息
     * @return 消费结果
     */
    public abstract MessageConsumeResultEnum onConsume(T payload);
}
