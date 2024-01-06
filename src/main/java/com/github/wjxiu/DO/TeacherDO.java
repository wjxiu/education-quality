package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教师表
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
public class TeacherDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "real_name")
    private Integer real_name;

    /**
     * 工号
     */
    @TableField(value = "teacher_no")
    private Integer teacher_no;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 电邮
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 是否删除 1为已经删除，0未删除
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    /**
     * 
     */
    @TableField(value = "password")
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}