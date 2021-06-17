package com.xingxin.service;

import com.xingxin.entity.RecordMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-17
 */
public interface RecordMessageService extends IService<RecordMessage> {

    /**
     * 查询失败的消息
     * @param limit
     * @return
     */
   List<RecordMessage> selectRecordMessageFailed(Integer limit);

}
