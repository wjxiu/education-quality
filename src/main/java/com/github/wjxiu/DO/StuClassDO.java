package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private Integer courseId;

    /**
     * 班级名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 关联的学院名字
     */
    @TableField(value = "department_name")
    private String departmentName;

    /**
     * 关联的专业名字
     */
    @TableField(value = "marjor_name")
    private String marjorName;

    /**
     * 创建时间
     */
    @TableField(value = "create_name")
    private Date createName;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 1 已删除 0未删除
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 教师id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;

    @TableField(exist = false)
    private static final long serialVersionUID = 134L;
}