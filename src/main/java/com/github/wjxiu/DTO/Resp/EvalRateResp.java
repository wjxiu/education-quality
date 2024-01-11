package com.github.wjxiu.DTO.Resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xiu
 * @create 2024-01-11 21:08
 */
@Data
public class EvalRateResp implements Serializable {
    Integer id;
    Integer teacherId;
    Integer stuClassId;
    Integer courseId;
    String courseName;
    Integer studentId;
    String studentName;
    String stuClassName;
    String teacherName;
    Integer stuId;
    LocalDateTime  createTime;
    LocalDateTime  updateTime;
    @Serial
    private static final long serialVersionUID = 42897L;
}
