package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.ChangePwdReq;
import com.github.wjxiu.DTO.LoginResp;

/**
* @author xiu
* @description 针对表【teacher(教师表)】的数据库操作Service
* @createDate 2024-01-06 21:21:31
*/
public interface TeacherService extends IService<TeacherDO> {

    LoginResp login(Integer id, String password);

    Integer register(TeacherDO teacherDO);

    Boolean changePwd(ChangePwdReq changePwdReq);
}
