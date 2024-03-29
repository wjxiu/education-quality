package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Req.StudentPageReq;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import com.github.wjxiu.DTO.Resp.LoginResp;

import java.util.List;

/**
* @author xiu
* @description 针对表【student(学生表)】的数据库操作Service
* @createDate 2024-01-06 21:21:24
*/
public interface StudentService extends IService<StudentDO> {

    LoginResp login(Integer id, String password);

    Integer register(StudentDO studentDO);

    Boolean changePwd(ChangePwdReq changePwdReq);

    List<EvalRateResp> getStuTeacherByStuId(Integer stuId);



    PageInfo<StudentDO> pageList(StudentPageReq studentDO, Integer pageNum, Integer pageSize);
    boolean updateById(StudentDO entity) ;



}
