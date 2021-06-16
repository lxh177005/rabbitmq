package com.xingxin.service.impl;

import com.xingxin.entity.Message;
import com.xingxin.dao.MessageMapper;
import com.xingxin.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
