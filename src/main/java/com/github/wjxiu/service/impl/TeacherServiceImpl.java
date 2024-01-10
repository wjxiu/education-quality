package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.LoginResp;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.common.token.UserInfoDTO;
import com.github.wjxiu.mapper.TeacherMapper;
import com.github.wjxiu.service.TeacherService;
import com.github.wjxiu.utils.JWTUtil;
import com.github.wjxiu.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【teacher(教师表)】的数据库操作Service实现
 * @createDate 2024-01-06 21:21:31
 */
@RequiredArgsConstructor
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherDO>
        implements TeacherService {
    final TeacherMapper teacherMapper;

    @Override
    public LoginResp login(Integer id, String password) {
        TeacherDO teacherDO = getById(id);
        if (teacherDO == null) throw new ClientException("没有这名用户");
        if (!PasswordUtil.verifyPassword(password, teacherDO.getPassword())) throw new ClientException("密码错误");
        String token = JWTUtil.generateAccessToken(new UserInfoDTO(id, teacherDO.getRealName(), 1, null));
        LoginResp loginResp = new LoginResp();
        loginResp.setId(id);
        loginResp.setName(teacherDO.getRealName());
        loginResp.setToken(token);
        return loginResp;
    }

    @Override
    public Integer register(TeacherDO teacherDO) {
        if (StringUtils.hasLength(teacherDO.getPhone()) && teacherMapper.selectCount(new LambdaQueryWrapper<TeacherDO>().eq(TeacherDO::getPhone, teacherDO.getPhone())) > 0)
            throw new ClientException("手机号重复");
        if (StringUtils.hasLength(teacherDO.getEmail()) && teacherMapper.selectCount(new LambdaQueryWrapper<TeacherDO>().eq(TeacherDO::getEmail, teacherDO.getEmail())) > 0)
            throw new ClientException("邮箱重复");
        String passwd = PasswordUtil.hashPassword(teacherDO.getPassword());
        teacherDO.setPassword(passwd);
        int insert = teacherMapper.insert(teacherDO);
        if (insert < 1) throw new ClientException("注册失败");
        return teacherDO.getId();
    }

    @Override
    public Boolean changePwd(ChangePwdReq changePwdReq) {
        if (!changePwdReq.getRepeatedPasswd().equals(changePwdReq.getNewPasswd()))
            throw new ClientException("两次密码不相同");
        Integer userId = UserContext.getUserId();
        TeacherDO teacherDO = getById(userId);
        String password = teacherDO.getPassword();
        if (!PasswordUtil.verifyPassword(changePwdReq.getOriginalPasswd(), password))
            throw new ClientException("原密码错误");
        String s = PasswordUtil.hashPassword(changePwdReq.getNewPasswd());
        teacherDO.setPassword(s);
        return teacherMapper.update(teacherDO, new LambdaQueryWrapper<TeacherDO>().eq(TeacherDO::getId, userId)) > 0;
    }

    public PageInfo<TeacherPageResp> selectTeacherList(TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        List<TeacherPageResp> teacherPageResps = teacherMapper.selectTeacherList(teacher, pageNum, pageSize);
       return new PageInfo<>(teacherPageResps);
    }
}




