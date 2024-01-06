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
 * @TableName major
 */
@TableName(value ="major")
@Data
public class MajorDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专业名称
     */
    @TableField(value = "major_name")
    private String major_name;

    /**
     * 关联的学院id
     */
    @TableField(value = "department_id")
    private Integer department_id;

    /**
     * 关联的学院名
     */
    @TableField(value = "department_name")
    private String department_name;

    /**
     * 创建时间
     */
    @TableField(value = "create_name")
    private Date create_name;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 1 已删除 0未删除
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}