package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学院表
 * @TableName department
 */
@TableName(value ="department")
@Data
public class DepartmentDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学院名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 修改时间
     */
    @TableField(value = "create_name")
    private Date create_name;

    /**
     * 1 已删除 0未删除
     */
    @TableField(value = "del_flag")
    private Integer del_flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}