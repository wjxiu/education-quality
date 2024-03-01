package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DO.StudentCourseClassTeacherDO;
import com.github.wjxiu.DO.TeacherDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiu
* @description 针对表【stu_class】的数据库操作Mapper
* @createDate 2024-01-06 21:21:17
* @Entity generator.DO.StuClassDO
*/
public interface StuClassMapper extends BaseMapper<StuClassDO> {

    List<StuClassDO> pageList(@Param("stuClass") StuClassDO stuClassDO,
                              @Param("pageNum") Integer pageNum,
                              @Param("pageSize") Integer pageSize);

    List<StuClassDO> getAllByTeacherId(Integer id);
}




