package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeDeSerializer;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

/**
 * 学生表
 * @TableName student
 */
@TableName(value ="student")
@Data
public class StudentDO implements Serializable {
    /**
     * 学号,也是id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 学生真实名字
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     *
     */
    @TableField(value = "password")
    private String password;

    /**
     * 专业名字
     */
    @TableField(value = "major_name")
    private String majorName;

    /**
     * 学院名字
     */
    @TableField(value = "department_name")
    private String departmentName;

    /**
     * 入学年份
     */
    @TableField(value = "enroll_year")
    private Integer enrollYear;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
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
    /**
     *
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 156L;
}