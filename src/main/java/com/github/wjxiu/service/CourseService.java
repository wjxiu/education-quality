package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.CourseDO;

import java.util.List;

/**
* @author xiu
* @description 针对表【course(课程表)】的数据库操作Service
* @createDate 2024-01-06 21:20:38
*/
public interface CourseService extends IService<CourseDO> {
   /**
    * 获取去重的课程名
    * @return
    */
   List<String> getAllName();
   Boolean courseNameisExit(String name);

    List<CourseDO> listPage(CourseDO course, Integer pageNum, Integer pageSize);

    @Override
     boolean updateById(CourseDO entity) ;
}
