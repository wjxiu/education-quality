package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName student_course_class_teacher
 */
@TableName(value ="student_course_class_teacher")
@Data
public class StudentCourseClassTeacherDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    @TableField(value = "stu_class_id")
    private Integer stuClassId;

    /**
     * 
     */
    @TableField(value = "student_name")
    private String studentName;

    /**
     * 
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 
     */
    @TableField(value = "stu_class_name")
    private String stuClassName;

    /**
     * 
     */
    @TableField(value = "teacher_name")
    private String teacherName;
    /**
     *
     */
    @TableField(value = "create_time",fill =FieldFill.INSERT )
    private Date createTime;

    /**
     *
     */
    @TableField(value = "update_time",fill =FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 231L;
}