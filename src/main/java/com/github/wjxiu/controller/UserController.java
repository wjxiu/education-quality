package com.github.wjxiu.controller;

import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Req.LoginReq;
import com.github.wjxiu.DTO.Req.UserRegisterReq;
import com.github.wjxiu.DTO.Resp.UserInfoResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.R;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.common.token.UserInfoDTO;
import com.github.wjxiu.service.StudentService;
import com.github.wjxiu.service.TeacherService;
import com.github.wjxiu.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserController {
    final StudentService studentService;
    final TeacherService teacherService;
    @PostMapping("/login")
    public R login(@Validated @RequestBody LoginReq loginReq){
        if (loginReq.getType()==0){
         return  R.success(studentService.login(loginReq.getId(),loginReq.getPassword()));
        }else if(loginReq.getType()==1){
            return  R.success(teacherService.login(loginReq.getId(),loginReq.getPassword(),1 ));
        } else if(loginReq.getType()==2){
            return  R.success(teacherService.login(loginReq.getId(),loginReq.getPassword(),2 ));
        }else{
            throw new ClientException("用户类型错误");
        }
    }
    @GetMapping("/register")
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
    @GetMapping("/info")
    public R userInfo(@NotBlank String token){
        UserInfoDTO userInfoDTO = JWTUtil.parseJwtToken(token);
        if (userInfoDTO != null&&userInfoDTO.getType()==0){
            StudentDO studentDO = studentService.getById(userInfoDTO.getUserId());
            UserInfoResp userInfoResp = new UserInfoResp();
            userInfoResp.setId(studentDO.getId());
            userInfoResp.setRoles("student");
            userInfoResp.setIsAdmin(false);
            userInfoResp.setIsStudent(true);
            userInfoResp.setType(0);
            userInfoResp.setRealName(studentDO.getRealName());
            return  R.success(userInfoResp);
        }else if(userInfoDTO.getType() == 1){
            UserInfoResp userInfoResp = new UserInfoResp();
            TeacherDO teacherDO = teacherService.getById(userInfoDTO.getUserId());
            userInfoResp.setId(teacherDO.getId());
            userInfoResp.setRoles("teacher");
            userInfoResp.setIsAdmin(false);
            userInfoResp.setType(1);
            userInfoResp.setIsStudent(false);
            userInfoResp.setRealName(teacherDO.getRealName());
            return  R.success(userInfoResp);
        }else if(userInfoDTO.getType() == 2){
            UserInfoResp userInfoResp = new UserInfoResp();
            TeacherDO teacherDO = teacherService.getById(userInfoDTO.getUserId());
            userInfoResp.setId(teacherDO.getId());
            userInfoResp.setRoles("admin");
            userInfoResp.setIsAdmin(true);userInfoResp.setType(2);
            userInfoResp.setIsStudent(false);
            userInfoResp.setRealName(teacherDO.getRealName());
            return  R.success(userInfoResp);
        }
         throw new ClientException("用户类型异常");

    }
    @PostMapping("/logout")
    public R logout(){
        UserContext.removeUser();
        return R.success();
    }
    @GetMapping("/getInfo")
    public String getInfo(){
        return UserContext.getRealName();
    }
}
