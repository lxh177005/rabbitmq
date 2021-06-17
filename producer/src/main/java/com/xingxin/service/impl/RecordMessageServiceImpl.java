package com.xingxin.service.impl;

import com.xingxin.entity.RecordMessage;
import com.xingxin.dao.RecordMessageMapper;
import com.xingxin.service.RecordMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-17
 */
@Service
public class RecordMessageServiceImpl extends ServiceImpl<RecordMessageMapper, RecordMessage> implements RecordMessageService {

    @Autowired
    private RecordMessageMapper recordMessageMapper;

    @Override
    public List<RecordMessage> selectRecordMessageFailed(Integer limit) {
        return recordMessageMapper.selectRecordMessageFailed(limit);
    }
}
