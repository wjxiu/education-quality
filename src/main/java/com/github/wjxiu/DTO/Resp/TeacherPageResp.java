package com.github.wjxiu.DTO.Resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.wjxiu.DO.CourseDO;
import com.github.wjxiu.DO.StuClassDO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiu
 * @create 2024-01-09 21:17
 */
@Data
public class TeacherPageResp {
    @TableField(value = "id")
    private String id;
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

    /**
     * 教授的课程
     */
    private List<StuClassDO> stuClassList=new ArrayList<>();
    @TableField(exist = false)
    private static final long serialVersionUID = 16707L;
}
