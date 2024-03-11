package com.github.wjxiu.DTO.Req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-13 8:21
 */
@TableName(value ="student")
@Data
public class StudentPageReq {
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
     * 入学年份的起始，包括
     */
    private Integer enrollYearStart=0;
    /**
     * 入学年份的结束，包括
     */
    private Integer enrollYearEnd=9999;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
    @TableField(value = "gendeer")
    private Integer gender;
}
