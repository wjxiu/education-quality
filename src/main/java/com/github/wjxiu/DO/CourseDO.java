package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程表
 * @TableName course
 */
@TableName(value ="course")
@Data
public class CourseDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教这门课的教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacher_id;

    /**
     * 课程名字
     */
    @TableField(value = "course_name")
    private String course_name;

    /**
     * 学院名字
     */
    @TableField(value = "department_name")
    private String department_name;

    /**
     * 专业名字
     */
    @TableField(value = "major_name")
    private String major_name;

    /**
     * 开始年份
     */
    @TableField(value = "start_year")
    private Integer start_year;

    /**
     * 上学期 0 下学期1
     */
    @TableField(value = "term")
    private Integer term;

    /**
     * 
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}