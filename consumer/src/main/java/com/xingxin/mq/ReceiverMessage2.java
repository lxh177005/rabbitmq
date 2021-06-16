package com.xingxin.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.xingxin.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author liuxh
 * @date 2021/6/16
 */
@Slf4j
@Component
public class ReceiverMessage2 {

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "msg_queue",durable = "true"),
//            exchange = @Exchange(name="msg_exchange",durable = "true",type = "topic"),
//            key = "msg.*"
//    )
//    )
//    @RabbitHandler
    public void receiverGetMessage(@Payload Message msg, @Headers Map<String,Object> headers, Channel channel) throws IOException {

        log.info("ReceiverMessage1 get message, msg is : {}", JSON.toJSONString(msg));

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        //ACK确认
        channel.basicAck(deliveryTag, false);
    }

}
