package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.github.wjxiu.conf.MyLocalDateTimeDeSerializer;
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
    @TableField(value = "gender")
    private Integer gender;
    @JsonDeserialize(using = MyLocalDateTimeDeSerializer.class)		// 反序列化
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonDeserialize(using = MyLocalDateTimeDeSerializer.class)		// 反序列化
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;
    @TableField(exist = false)
    private static final long serialVersionUID = 18677L;
}