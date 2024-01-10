package com.github.wjxiu.DTO.Resp;

import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.TeacherDO;
import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-08 10:33
 */
@Data
public class UserRegisterReq {
    StudentDO studentDO;
    TeacherDO teacherDO;
}
