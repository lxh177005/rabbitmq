package com.xingxin.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
public class ReceiverMessage1 {

    //配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "msg_queue",durable = "true"),
            exchange = @Exchange(name="msg_exchange",durable = "true",type = "topic"),
            key = "msg.*"
    )
    )
    @RabbitHandler//如果有消息过来，在消费的时候调用这个方法
    public void receiverGetMessage(@Payload JSONObject msg, @Headers Map<String,Object> headers, Channel channel) throws IOException {

        log.info("ReceiverMessage1 get message, msg is : {}", JSON.toJSONString(msg));

        //表示消息投递序号，每次消费消息或者消息重新投递后，deliveryTag都会增加。
        // 手动消息确认模式下，我们可以对指定deliveryTag的消息进行ack、nack、reject等操作。
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        /*
         * ACK,确认一条消息已经被消费,之后消息在队列里被删掉，防止重复消费
         * 参数2：是否批量确认，值为 true 则会一次性 ack所有小于当前消息 deliveryTag 的消息。
         * 举个栗子： 假设我先发送三条消息deliveryTag分别是5、6、7，可它们都没有被确认，
         * 当我发第四条消息此时deliveryTag为8，multiple设置为 true，会将5、6、7、8的消息全部进行确认。
         */
        channel.basicAck(deliveryTag, false);

    }

}
