package com.xingxin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author mrLiu
 * @since 2021-06-16
 */
@TableName("message")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private static final long serialVersionUID = -935185608005918820L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private String msgId;

    private String msgData;

    private Integer msgStatus;

    private Date creatTime;

    private Date updateTime;

}
