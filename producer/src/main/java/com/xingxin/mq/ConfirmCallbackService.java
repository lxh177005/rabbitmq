package com.xingxin.mq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingxin.entity.Message;
import com.xingxin.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liuxh
 * @date 2021/6/15
 * 消息只要被 rabbitmq broker 接收到就会触发 confirmCallback 回调
 */
@Slf4j
@Component
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private MessageService messageService;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String status = "";
        if (ack){
            log.info("message ack true , correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
            status = "1";
        }else {
            log.error("message ack false !");
            status = "2";
        }
        //更新消息状态
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        Message msg = new Message();
        msg.setMsgId(correlationData.getId());
        wrapper.setEntity(msg);

        Message message = new Message();
        //状态0 发送中 1发送成功 2发送失败
        message.setMsgStatus(status);
        message.setUpdateTime(new Date());
        messageService.update(message ,wrapper);
    }
}
