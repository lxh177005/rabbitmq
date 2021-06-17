package com.xingxin.mq;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingxin.entity.RecordMessage;
import com.xingxin.service.RecordMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liuxh
 * @date 2021/6/16
 * 如果消息未能投递到指定的queue里，就会触发回调returnCallback
 * 场景：exchange和queue没有绑定
 */
@Slf4j
@Component
public class ReturnCallbackService implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RecordMessageService recordMessageService;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("message get to queue failed!  message={} ,replyCode={} ,replyText={} ,exchange={} ,routingKey={}",
                JSON.toJSONString(message), replyCode, replyText, exchange, routingKey);
        /**
         * 记录当前消息的详细投递数据，方便后续做重发或者补偿等操作。
         */
        Object obj = message.getMessageProperties().getHeader("spring_returned_message_correlation");
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        QueryWrapper<RecordMessage> wrapper = new QueryWrapper<>();
        //select db
        RecordMessage recordMessage = new RecordMessage();
        recordMessage.setMsgId(String.valueOf(obj));
        wrapper.setEntity(recordMessage);
        RecordMessage recordMsg = recordMessageService.query().getBaseMapper().selectOne(wrapper);

        RecordMessage rm = new RecordMessage();
        rm.setExchange(exchange);
        rm.setRoutingKey(routingKey);
        rm.setStatus(2);
        if (ObjectUtils.isNotEmpty(recordMsg)) {
            //update
            rm.setUpdateTime(new Date());
            recordMessageService.update(rm, wrapper);
            return;
        }
        rm.setTryCount(0);
        rm.setCreateTime(new Date());
        rm.setMsgId(String.valueOf(obj));
        recordMessageService.save(rm);
    }
}
