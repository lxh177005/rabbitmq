package com.xingxin.controller;


import com.xingxin.entity.Message;
import com.xingxin.mq.SendMessage;
import com.xingxin.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-16
 */
@Slf4j
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private SendMessage sendMessage;

    @GetMapping("/send")
    public boolean sendMessageTest() {

        try {
            for (int i = 0; i < 100; i++) {
                Message msg = new Message();
                msg.setMsgId(UUID.randomUUID().toString());
                msg.setMsgData("wahaha");
                //状态0 发送中 1发送成功 2发送失败
                msg.setMsgStatus(0);
                msg.setTryCount(0);
                msg.setCreatTime(new Date());
                boolean save = messageService.save(msg);
                if (!save) {
                    return false;
                }
                log.info("sendMessage start... msg is : {}", msg);
                sendMessage.sendMessage("msg_exchange", "msg.abc", msg, msg.getMsgId());
            }
        } catch (Exception e) {
            log.error("sendMessage Exception is: ", e);
            return false;
        }
        return true;
    }
}

