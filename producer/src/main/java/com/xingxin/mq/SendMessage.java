package com.xingxin.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxh
 * @date 2021/6/16
 */
@Slf4j
@Component
public class SendMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ConfirmCallbackService confirmCallbackService;
    @Autowired
    private ReturnCallbackService returnCallbackService;

    public void sendMessage(String exchange, String routingKey, Object msg, String id) {
        //确保消息发送失败后可以重新返回到队列中,注意：yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);

        //消费者确认收到消息后，手动ack回执回调处理
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        //消息投递到队列失败回调处理
        rabbitTemplate.setReturnCallback(returnCallbackService);

        //发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey,
                JSONObject.toJSONString(msg), new CorrelationData(id));
    }

}
