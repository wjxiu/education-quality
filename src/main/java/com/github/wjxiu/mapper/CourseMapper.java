package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.CourseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiu
* @description 针对表【course(课程表)】的数据库操作Mapper
* @createDate 2024-01-06 21:20:38
* @Entity generator.DO.Course
*/
public interface CourseMapper extends BaseMapper<CourseDO> {
    public CourseDO selectAllByTeacherId(@Param("teacherId") Integer teacherId);

    List<CourseDO> listPage(@Param("course") CourseDO course,
                           @Param("pageNum") Integer pageNum,
                           @Param("pageSize") Integer pageSize);
}




