package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.ChangePwdReq;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.LoginResp;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.common.token.UserContext;
import com.github.wjxiu.common.token.UserInfoDTO;
import com.github.wjxiu.mapper.StuClassMapper;
import com.github.wjxiu.mapper.StudentCourseClassTeacherMapper;
import com.github.wjxiu.mapper.TeacherMapper;
import com.github.wjxiu.service.TeacherService;
import com.github.wjxiu.utils.JWTUtil;
import com.github.wjxiu.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    final StudentCourseClassTeacherMapper studentCourseClassTeacherMapper;
    final StuClassMapper stuClassMapper;
    
    @Override
    public LoginResp login(Integer id, String password, Integer type) {
        TeacherDO teacherDO = getById(id);
        if (teacherDO == null) throw new ClientException("没有这名用户");
        if (!PasswordUtil.verifyPassword(password, teacherDO.getPassword())) throw new ClientException("密码错误");
        if (type==2&&teacherDO.getAdminFlag()==0)throw new ClientException("该教师不是管理员");
        String token = JWTUtil.generateAccessToken(new UserInfoDTO(id, teacherDO.getRealName(),type ,null));
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
        if (!changePwdReq.getNewPassword().equals(changePwdReq.getConfirmPassword()))
            throw new ClientException("两次密码不相同");
        Integer userId = UserContext.getUserId();
        TeacherDO teacherDO = getById(userId);
        String password = teacherDO.getPassword();
        if (!PasswordUtil.verifyPassword(changePwdReq.getOldPassword(), password))
            throw new ClientException("原密码错误");
        String s = PasswordUtil.hashPassword(changePwdReq.getConfirmPassword());
        teacherDO.setPassword(s);
        return teacherMapper.update(teacherDO, new LambdaQueryWrapper<TeacherDO>().eq(TeacherDO::getId, userId)) > 0;
    }

    public PageInfo<TeacherPageResp> selectTeacherList(TeacherPageReq teacher, Integer pageNum, Integer pageSize) {
        List<TeacherDO> list= teacherMapper.selectTeacherList1( teacher,  pageNum,  pageSize);
        PageInfo<TeacherDO> temp = new PageInfo<>(list);
        ArrayList<TeacherPageResp> res = new ArrayList<>();
        for (TeacherDO teacherDO : list) {
            List<StuClassDO> allByTeacherId = stuClassMapper.getAllByTeacherId(teacherDO.getId());
            TeacherPageResp teacherPageResp = new TeacherPageResp();
            BeanUtils.copyProperties(teacherDO,teacherPageResp);
            teacherPageResp.setId(teacherDO.getId().toString());
            teacherPageResp.setStuClassList(allByTeacherId.stream().distinct().toList());
            res.add(teacherPageResp);
        }
        PageInfo<TeacherPageResp> pageInfo = new PageInfo<TeacherPageResp>();
        BeanUtils.copyProperties(temp,pageInfo);
        pageInfo.setList(res);

       return pageInfo;
    }

    @Override
    public List<StuClassDO> getTeacherClasses(Integer teacherId) {
       return  stuClassMapper.selectList(
                new LambdaQueryWrapper<StuClassDO>().eq(StuClassDO::getTeacherId, teacherId).select(StuClassDO::getId,StuClassDO::getName));
    }
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean updateById(TeacherDO entity) {
//        更新
        List<StuClassDO> allByTeacherId = stuClassMapper.getAllByTeacherId(entity.getId());
        for (StuClassDO stuClassDO : allByTeacherId) {
            stuClassDO.setTeacherName(entity.getRealName());
            int i1 = stuClassMapper.updateById(stuClassDO);
            if (i1==0)throw new ClientException("更新教师失败");
        }
        List<StudentCourseClassTeacherDO> allByTeacherId1 = studentCourseClassTeacherMapper.getAllByTeacherId(entity.getId());
        for (StudentCourseClassTeacherDO studentCourseClassTeacherDO : allByTeacherId1) {
            studentCourseClassTeacherDO.setTeacherName(entity.getRealName());
            int i1 = studentCourseClassTeacherMapper.updateById(studentCourseClassTeacherDO);
            if (i1==0)throw new ClientException("更新教师失败");
        }
        if (Objects.equals(entity.getPassword(), "")){
            entity.setPassword(null);
        }else{
            entity.setPassword(PasswordUtil.hashPassword(entity.getPassword()));
        }
        boolean b = super.updateById(entity);
        if (!b)throw new ClientException("更新教师失败");
        return true;
    }

    @Override
    public List<StuClassDO> getTeacherCourse(Integer teacherId) {
        return  stuClassMapper.selectList(new LambdaQueryWrapper<StuClassDO>().eq(StuClassDO::getTeacherId, teacherId).select(StuClassDO::getCourseName, StuClassDO::getCourseId)).stream().distinct().toList();
    }
    /**
     * 获取教师教授班级的年份列表
     *
     * @param teacherIds
     * @return
     */
    @Override
    public List<Integer> getTeacherYear(Integer teacherIds) {
        return  stuClassMapper.selectList(new LambdaQueryWrapper<StuClassDO>().eq(StuClassDO::getTeacherId, teacherIds).select(StuClassDO::getCourseName, StuClassDO::getStartYear)).stream().map(StuClassDO::getStartYear).distinct().sorted(Comparator.reverseOrder()).toList();

    }
}




