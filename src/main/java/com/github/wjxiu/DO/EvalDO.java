package com.github.wjxiu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 评分选项表
 * @TableName eval
 */
@TableName(value ="eval")
@Data
public class EvalDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 选项标题
     */
    @TableField(value = "eval_item")
    private String evalItem;

    /**
     * 选项说明
     */
    @TableField(value = "explanation")
    private String explanation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}