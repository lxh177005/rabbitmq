package com.xingxin.service;

import com.xingxin.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-16
 */
public interface MessageService extends IService<Message> {

    List<Message> selectMessageFailed(int limit);
}
