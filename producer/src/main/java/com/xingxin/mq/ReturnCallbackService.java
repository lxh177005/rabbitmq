package com.xingxin.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author liuxh
 * @date 2021/6/16
 * 如果消息未能投递到目标 queue 里将触发回调 returnCallback
 */
@Slf4j
@Component
public class ReturnCallbackService implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("returnedMessage failed  ===> replyCode={} ,replyText={} ,exchange={} ,routingKey={}",
                replyCode, replyText, exchange, routingKey);
        /**
         * 记录当前消息的详细投递数据，方便后续做重发或者补偿等操作。
         */

    }
}
