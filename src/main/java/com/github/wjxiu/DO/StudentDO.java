package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    @TableId(value = "id")
    private Integer id;

    /**
     * 学生真实名字
     */
    @TableField(value = "real_name")
    private String real_name;

    /**
     * 
     */
    @TableField(value = "password")
    private String password;

    /**
     * 专业名字
     */
    @TableField(value = "major_name")
    private Integer major_name;

    /**
     * 学院名字
     */
    @TableField(value = "department_name")
    private String department_name;

    /**
     * 入学年份
     */
    @TableField(value = "enroll_year")
    private Integer enroll_year;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Integer create_time;

    /**
     * 
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}