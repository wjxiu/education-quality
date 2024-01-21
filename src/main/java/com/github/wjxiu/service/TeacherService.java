package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.LoginResp;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;

import java.util.List;

/**
* @author xiu
* @description 针对表【teacher(教师表)】的数据库操作Service
* @createDate 2024-01-06 21:21:31
*/
public interface TeacherService extends IService<TeacherDO> {

    LoginResp login(Integer id, String password);

    Integer register(TeacherDO teacherDO);

    Boolean changePwd(ChangePwdReq changePwdReq);


    PageInfo<TeacherPageResp> selectTeacherList(TeacherPageReq teacher, Integer pageNum, Integer pageSize);

    List<StudentCourseClassTeacherDO> getTeacherClasses(Integer teacherId);
}
