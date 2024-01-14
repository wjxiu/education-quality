package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.CourseDO;
import com.github.wjxiu.mapper.CourseMapper;
import com.github.wjxiu.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【course(课程表)】的数据库操作Service实现
 * @createDate 2024-01-06 21:20:38
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDO>
        implements CourseService {
    @Override
    public List<String> getAllName() {
        return list(new LambdaQueryWrapper<CourseDO>().select(CourseDO::getCourseName))
                .stream()
                .map(CourseDO::getCourseName)
                .distinct()
                .toList();
    }

    @Override
    public Boolean courseNameisExit(String name) {
        return  count(new LambdaQueryWrapper<CourseDO>().eq(CourseDO::getCourseName, name))>0;

    }
}




