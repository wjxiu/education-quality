package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
import lombok.Data;

/**
 * 这个不是关系表，为了避开java关键字
 * @TableName stu_class
 */
@TableName(value ="stu_class")
@Data
public class StuClassDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Integer courseId;

    /**
     * 教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;
    /**
     * 教师名
     */
    @TableField(value = "teacher_name")
    private String teacherName;
    /**
     * 班级名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 关联的学院名字
     */
    @TableField(value = "department_name")
    private String departmentName;

    /**
     * 关联的专业名字
     */
    @TableField(value = "major_name")
    private String majorName;
    /**
     * 关联的专业名字
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 创建时间
     */
    @TableField(value = "create_name")
    private Date createName;
    /**
     * 1 已删除 0未删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}