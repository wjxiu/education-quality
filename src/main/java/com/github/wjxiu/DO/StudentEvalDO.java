package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

/**
 * 
 * @TableName student_eval
 */
@TableName(value ="student_eval")
@Data
public class StudentEvalDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @TableField(value = "stu_id")
    private Integer stuId;

    /**
     * 教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 选项
     */
    @TableField(value = "eval_id")
    private Integer evalId;

    /**
     * 教的班级id
     */
    @TableField(value = "stu_class_id")
    private Integer stuClassId;

    /**
     * 评分
     */
    @TableField(value = "rate")
    private Integer rate;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}