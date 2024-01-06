package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName stu_class
 */
@TableName(value ="stu_class")
@Data
public class StuClassDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private Integer course_id;

    /**
     * 入学年方
     */
    @TableField(value = "enroll_year")
    private Integer enroll_year;

    /**
     * 班级名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 关联的学院名字
     */
    @TableField(value = "department_name")
    private String department_name;

    /**
     * 关联的专业名字
     */
    @TableField(value = "marjor_name")
    private String marjor_name;

    /**
     * 创建时间
     */
    @TableField(value = "create_name")
    private Date create_name;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 1 已删除 0未删除
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    /**
     * 教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacher_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}