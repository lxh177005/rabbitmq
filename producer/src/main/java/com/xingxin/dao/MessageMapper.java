package com.xingxin.dao;

import com.xingxin.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-16
 */
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectMessageFailed(int limit);
}
