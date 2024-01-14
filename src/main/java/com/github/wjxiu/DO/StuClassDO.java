package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 1 已删除 0未删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}