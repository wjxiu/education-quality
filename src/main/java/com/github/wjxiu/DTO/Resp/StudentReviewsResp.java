package com.github.wjxiu.DTO.Resp;

import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import lombok.Data;

/**
 * @author xiu
 * @create 2024-03-01 11:53
 */
@Data
public class StudentReviewsResp {
    StudentCourseClassTeacherDO relation;
    StuClassDO stuClass;

}
