package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.*;

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
     * 课程名字
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 学院名字
     */
    @TableField(value = "department_name")
    private String departmentName;

    /**
     * 专业名字
     */
    @TableField(value = "major_name")
    private String majorName;

    /**
     * 开始年份
     */
    @TableField(value = "start_year")
    private Integer startYear;

    /**
     * 上学期  下学期 全年
     */
    @TableField(value = "Term")
    private String term;

    /**
     *
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}