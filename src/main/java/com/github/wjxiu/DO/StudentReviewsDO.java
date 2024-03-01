package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeDeSerializer;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xiu
 * @create 2024-02-29 17:28
 */
@TableName(value ="student_reviews")
@Data
@JsonIgnoreProperties(value = { "createTime","updateTime" }, allowSetters = true)
public class StudentReviewsDO implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    int id;
    /**
     *
     */
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     *
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     *
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     *
     */
    @TableField(value = "evaluate")
    private String evaluate;
    @JsonDeserialize(using = MyLocalDateTimeDeSerializer.class)		// 反序列化
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonDeserialize(using = MyLocalDateTimeDeSerializer.class)		// 反序列化
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 13244L;
}
