package com.github.wjxiu.DTO.Resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.github.wjxiu.conf.MyLocalDateTimeDeSerializer;
import com.github.wjxiu.conf.MyLocalDateTimeSerializer;
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
    @JsonDeserialize(using = MyLocalDateTimeDeSerializer.class)		// 反序列化
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    LocalDateTime  createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = MyLocalDateTimeDeSerializer.class)		// 反序列化
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    LocalDateTime  updateTime;
    @Serial
    private static final long serialVersionUID = 42897L;
}
