package com.xingxin.dao;

import com.xingxin.entity.RecordMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-17
 */
@Repository
public interface RecordMessageMapper extends BaseMapper<RecordMessage> {

    List<RecordMessage> selectRecordMessageFailed(Integer limit);
}
