package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 教师表
 *
 * @TableName teacher
 */
@TableName(value = "teacher")
@Data
public class TeacherDO implements Serializable {
    /**
     * 是唯一标识也是工号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "real_name")
    private String realName;
    /**
     *
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 电邮
     */
    @TableField(value = "email")
    private String email;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     *
     */
    @TableField(value = "password")
    private String password;

    /**
     * 是否为管理员,1为是,0为否
     */
    @TableField(value = "admin_flag")
    private Integer adminFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 18677L;
}