package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName student_eval
 */
@TableName(value ="student_eval")
@Data
public class StudentEvalDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @TableField(value = "stu_id")
    private Integer stuId;

    /**
     * 教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    /**
     * 选项
     */
    @TableField(value = "eval_id")
    private Integer evalId;

    /**
     * 教的班级id
     */
    @TableField(value = "stu_class_id")
    private Integer stuClassId;

    /**
     * 评分
     */
    @TableField(value = "rate")
    private Integer rate;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}