package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.CourseDO;
import com.github.wjxiu.DO.MajorDO;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.common.Exception.ClientException;
import com.github.wjxiu.mapper.MajorMapper;
import com.github.wjxiu.service.CourseService;
import com.github.wjxiu.service.MajorService;
import com.github.wjxiu.service.StuClassService;
import com.github.wjxiu.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author xiu
* @description 针对表【major】的数据库操作Service实现
* @createDate 2024-01-06 21:21:08
*/
@Service
@RequiredArgsConstructor
public class MajorServiceImpl extends ServiceImpl<MajorMapper, MajorDO>
    implements MajorService {
    final MajorMapper mapper;
    final StudentService studentService;
    final CourseService courseService;
    final StuClassService stuClassService;
    @Override
    public List<MajorDO> pageList(MajorDO majorDO, Integer pageNum, Integer pageSize) {
       return  mapper.pageList(majorDO,pageNum,pageSize);
    }
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean updateById(MajorDO entity) {
//        更新对应的关联的学生表，班级表，课程表
        MajorDO old = getById(entity.getId());
        String oldMajorName = old.getMajorName();
        String newMajorName = entity.getMajorName();
        List<StudentDO> list = studentService.list(new LambdaQueryWrapper<StudentDO>().eq(StudentDO::getMajorName, oldMajorName));
        studentService.updateBatchById(list);
        list.forEach(stu->stu.setMajorName(newMajorName));
        List<StuClassDO> stuClassDOList = stuClassService.list(new LambdaQueryWrapper<StuClassDO>().eq(StuClassDO::getMajorName, oldMajorName));
        stuClassDOList.forEach(stuClassDO -> stuClassDO.setMajorName(newMajorName));
        boolean b1 = stuClassService.updateBatchById(stuClassDOList);
        List<CourseDO> courseDOList = courseService.list(new LambdaQueryWrapper<CourseDO>().eq(CourseDO::getMajorName, oldMajorName));
        courseDOList.forEach(courseDO -> courseDO.setMajorName(newMajorName));
        boolean b = courseService.updateBatchById(courseDOList);
        boolean b2 = super.updateById(entity);
        if (!(b&&b1&&b2))throw new ClientException("更新专业过程中出现异常");
        return true;
    }
}




