package com.xingxin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-17
 */
@TableName("record_message")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecordMessage implements Serializable {

    private static final long serialVersionUID=2L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 重试次数
     */
    private Integer tryCount;

    /**
     * 状态 0-发送中 1-成功 2-失败
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

}
