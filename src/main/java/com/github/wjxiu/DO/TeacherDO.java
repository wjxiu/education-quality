package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 教师表
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
public class TeacherDO implements Serializable {
    /**
     *是唯一标识也是工号
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "real_name")
    private String realName;
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
    private Date createTime;

    /**
     *
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除 1为已经删除，0未删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     *
     */
    @TableField(value = "password")
    private String password;

    /**
     * 是否为管理员,1为是,0为否
     */
    @TableField(value = "admin_flag")
    private Integer adminFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1677L;
}