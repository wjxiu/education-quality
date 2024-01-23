package com.github.wjxiu.DTO.Req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.wjxiu.DO.CourseDO;
import lombok.Data;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-09 21:37
 */
@Data
public class TeacherPageReq {
    /**
     *
     */
    private String realName;
    private String id;
    private Integer adminFlag;
}
