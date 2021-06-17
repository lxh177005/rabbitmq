package com.xingxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingxin.dao.MessageMapper;
import com.xingxin.entity.Message;
import com.xingxin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> selectMessageFailed(int limit) {
        return messageMapper.selectMessageFailed(limit);
    }
}
