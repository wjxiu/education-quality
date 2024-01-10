package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Resp.LoginResp;

/**
* @author xiu
* @description 针对表【student(学生表)】的数据库操作Service
* @createDate 2024-01-06 21:21:24
*/
public interface StudentService extends IService<StudentDO> {

    LoginResp login(Integer id, String password);

    Integer register(StudentDO studentDO);

    Boolean changePwd(ChangePwdReq changePwdReq);
}
