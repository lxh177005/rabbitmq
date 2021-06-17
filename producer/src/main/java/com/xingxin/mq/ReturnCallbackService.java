package com.xingxin.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author liuxh
 * @date 2021/6/16
 * 如果消息未能投递到指定的queue里，就会触发回调returnCallback
 * 场景：exchange和queue没有绑定
 */
@Slf4j
@Component
public class ReturnCallbackService implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("message get to queue failed!  message={} ,replyCode={} ,replyText={} ,exchange={} ,routingKey={}",
                JSON.toJSONString(message) ,replyCode, replyText, exchange, routingKey);
        /**
         * 记录当前消息的详细投递数据，方便后续做重发或者补偿等操作。
         */

    }
}
