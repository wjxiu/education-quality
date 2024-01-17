package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

/**
 * 评分选项表
 * @TableName eval
 */
@TableName(value ="eval")
@Data
public class EvalDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 选项标题
     */
    @TableField(value = "eval_item")
    private String evalItem;

    /**
     * 选项说明
     */
    @TableField(value = "explanation")
    private String explanation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}