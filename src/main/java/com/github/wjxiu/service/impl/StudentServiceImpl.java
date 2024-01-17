        package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Req.StudentPageReq;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import com.github.wjxiu.DTO.Resp.LoginResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.common.token.UserInfoDTO;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.mapper.StudentMapper;
import com.github.wjxiu.service.StudentEvalService;
import com.github.wjxiu.service.StudentService;
import com.github.wjxiu.utils.JWTUtil;
import com.github.wjxiu.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【student(学生表)】的数据库操作Service实现
 * @createDate 2024-01-06 21:21:24
 */
@Service
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO>
        implements StudentService {
    final StudentMapper studentMapper;
    final StudentEvalService studentEvalService;
    final StudentCourseClassTeacherMapper studentCourseClassTeacherMapper;
    @Override
    public LoginResp login(Integer id, String password) {
        log.info("测试");
        StudentDO stu = getById(id);
        if (stu == null) throw new ClientException("没有这名用户");
        if (!PasswordUtil.verifyPassword(password,stu.getPassword())) throw new ClientException("密码错误");
        String token = JWTUtil.generateAccessToken(new UserInfoDTO(id, stu.getRealName(),0, null));
        LoginResp loginResp = new LoginResp();
        loginResp.setId(id);
        loginResp.setName(stu.getRealName());
        loginResp.setToken(token);
        return loginResp;
    }

    @Override
    public Integer register(StudentDO studentDO) {
        if (StringUtils.hasLength(studentDO.getEmail())&&studentMapper.selectCount(new LambdaQueryWrapper<StudentDO>().eq(StudentDO::getEmail,studentDO.getEmail()))>0) throw new ClientException("邮箱重复");
        String passwd = PasswordUtil.hashPassword(studentDO.getPassword());
        studentDO.setPassword(passwd);
        int insert = studentMapper.insert(studentDO);
        if (insert<1)throw new ClientException("注册失败");
        return studentDO.getId();
    }

    @Override
    public Boolean changePwd(ChangePwdReq changePwdReq) {
        if (!changePwdReq.getRepeatedPasswd().equals(changePwdReq.getNewPasswd()))throw new ClientException("两次密码不相同");
        Integer userId = UserContext.getUserId();
        StudentDO studentDO = getById(userId);
        String password = studentDO.getPassword();
        if (!PasswordUtil.verifyPassword(changePwdReq.getOriginalPasswd(),password)) throw new ClientException("原密码错误");
        String s = PasswordUtil.hashPassword(changePwdReq.getNewPasswd());
        studentDO.setPassword(s);
        return  studentMapper.update(studentDO,new LambdaQueryWrapper<StudentDO>().eq(StudentDO::getId,userId))>0;
    }

    @Override
    public List<EvalRateResp> getStuTeacherByStuId(Integer stuId) {
        if (stuId ==null||stuId==0)stuId=UserContext.getUserId();
        return studentMapper.getAllTeacher(stuId);
    }



    @Override
    public PageInfo<StudentDO> pageList(StudentPageReq studentPageReq, Integer pageNum, Integer pageSize) {
        return new PageInfo<StudentDO>(studentMapper.pageList( studentPageReq,  pageNum,  pageSize));
    }

    @Override
    public boolean updateById(StudentDO entity) {
        String password = entity.getPassword();
        StudentDO studentDO = getById(entity.getId());
        if (password.isEmpty()){
            entity.setPassword(studentDO.getPassword());
            return super.updateById(entity);
        }
        String encrptPasswd = PasswordUtil.hashPassword(password);
        entity.setPassword(encrptPasswd);
      return super.updateById(entity);
    }




    @Override
    public boolean save(StudentDO entity) {
        String password = entity.getPassword();
        if (password.isEmpty()) throw new ClientException("密码为空");
        String encrptPasswd = PasswordUtil.hashPassword(password);
        entity.setPassword(encrptPasswd);
        return super.save(entity);
    }
}




