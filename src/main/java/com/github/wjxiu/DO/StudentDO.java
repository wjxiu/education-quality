package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
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

    /**
     *
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     *
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     *
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 156L;
}