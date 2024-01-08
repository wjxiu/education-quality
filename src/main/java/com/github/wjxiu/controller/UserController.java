package com.github.wjxiu.controller;

import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.ChangePwdReq;
import com.github.wjxiu.DTO.UserRegisterReq;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.service.StudentService;
import com.github.wjxiu.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author xiu
 * @create 2024-01-06 21:55
 */
@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final StudentService studentService;
    final TeacherService teacherService;
    @PostMapping("/login")
    public R login(Integer id, String password, Integer type){
        if (type==0){
         return  R.success(studentService.login(id,password));
        }else if(type==1){
            return  R.success(teacherService.login(id,password));
        }else{
            throw new ClientException("用户类型错误");
        }
    }
    @PostMapping("/register")
    public R register(@RequestBody(required = false) UserRegisterReq userRegisterReq, Integer type){
        if (type==0&&userRegisterReq.getStudentDO()!=null){
            return  R.success(studentService.register(userRegisterReq.getStudentDO()));
        }else if(type==1&&userRegisterReq.getTeacherDO()!=null){
            return  R.success(teacherService.register(userRegisterReq.getTeacherDO()));
        }else{
            throw new ClientException("用户类型错误");
        }
    }
    @PostMapping("/changepasswd")
    public R changepasswd(@Validated ChangePwdReq changePwdReq,Integer type){
        if (type==0){
            return  R.success(studentService.changePwd(changePwdReq));
        }else if(type==1){
            return  R.success(teacherService.changePwd(changePwdReq));
        }else{
            throw new ClientException("用户类型错误");
        }
    }
    @PostMapping("/logout")
    public R logout(){
        UserContext.removeUser();
        return R.success();
    }
}
