package com.github.wjxiu.service;

/**
 * @author xiu
 * @create 2024-01-11 21:58
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DO.StuClassDO;

/**
 * @author xiu
 * @description 针对表【student_eval】的数据库操作Service
 * @createDate 2024-01-11 21:56:30
 */
public interface StudentEvalService extends IService<StudentEvalDO> {

    /**
    * @author xiu
    * @description 针对表【stu_class(这个不是关系表，为了避开java关键字)】的数据库操作Service
    * @createDate 2024-01-14 00:08:53
    */
    interface StuClassService extends IService<StuClassDO> {

    }
}
