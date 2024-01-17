package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

/**
 * 学院表
 * @TableName department
 */
@TableName(value ="department")
@Data
public class DepartmentDO implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学院名称
     */
    @TableField(value = "name")
    private String name;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 1 已删除 0未删除
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 12L;
}