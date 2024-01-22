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
    String teacherName;
    Integer stuClassId;
    String stuClassName;
    Integer courseId;
    String courseName;
    Integer studentId;
    String studentName;
    String evalItem;
    Integer evalId;
    Integer rate;
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    LocalDateTime  createTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
//    LocalDateTime  updateTime;
    @Serial
    private static final long serialVersionUID = 42897L;
}
