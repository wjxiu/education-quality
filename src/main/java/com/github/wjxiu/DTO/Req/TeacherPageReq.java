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
    @TableField(value = "real_name")
    private String realName;
    @TableField(value = "admin_flag")
    private Integer adminFlag;

    /**
     * 学院名字
     */
    @TableField(value = "department_name")
    private String departmentName;
    /**
     * 专业名字
     */
    @TableField(value = "major_name")
    private String majorName;
    /**
     * 开始年份
     */
    @TableField(value = "start_year")
    private Integer startYear;
    /**
     * 上学期 0 下学期1
     */
    @TableField(value = "term")
    private Integer term;
}
