package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

/**
 * 
 * @TableName major
 */
@TableName(value ="major")
@Data
public class MajorDO implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专业名称
     */
    @TableField(value = "major_name")
    private String majorName;

    /**
     * 关联的学院id
     */
    @TableField(value = "department_id")
    private Integer departmentId;

    /**
     * 关联的学院名
     */
    @TableField(value = "department_name")
    private String departmentName;

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
    private static final long serialVersionUID = 133L;
}