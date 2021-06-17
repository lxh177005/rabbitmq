package com.xingxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingxin.entity.Message;
import com.xingxin.entity.RecordMessage;
import com.xingxin.mq.SendMessage;
import com.xingxin.service.MessageService;
import com.xingxin.service.RecordMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuxh
 * @date 2021/6/17
 */
@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private RecordMessageService recordMessageService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SendMessage sendMessage;

    /**
     * 定时任务-对投递到交换机失败的消息进行补偿，重试超过3次放弃
     */
    @GetMapping("/message")
    @Scheduled(cron = "5/10 * * * * ?")
    public void messageTask() {
        log.info("----messageTask start-----------");
        List<Message> messages = messageService.selectMessageFailed(10);
        for (Message msg : messages) {
            log.info("sendMessage start... msg is : {}", msg);
            sendMessage.sendMessage("msg_exchange", "msg.abc", msg, msg.getMsgId());
        }
    }

    /**
     * 定时任务-对未投递到队列的消息进行补偿，重试超过3次放弃
     */
    @GetMapping("/record/message")
    @Scheduled(cron = "0/10 * * * * ?")
    public void recordMessageTask() {
        log.info("---------recordMessageTask start--------------");
        List<RecordMessage> recordMessages = recordMessageService.selectRecordMessageFailed(10);
        for (RecordMessage recordMessage : recordMessages) {
            //select message
            QueryWrapper<Message> wrapper = new QueryWrapper<>();
            Message message = new Message();
            message.setMsgId(recordMessage.getMsgId());
            wrapper.setEntity(message);
            Message msg = messageService.getBaseMapper().selectOne(wrapper);
            //update status = 1
            QueryWrapper<RecordMessage> rmWrapper = new QueryWrapper<>();
            RecordMessage rmNew = new RecordMessage();
            rmNew.setStatus(1);
            rmNew.setTryCount(recordMessage.getTryCount()+1);
            rmWrapper.setEntity(recordMessage);
            recordMessageService.update(rmNew,rmWrapper);
            //record message
            sendMessage.sendMessage(recordMessage.getExchange(),recordMessage.getRoutingKey(), msg, recordMessage.getMsgId());
        }
    }

}
