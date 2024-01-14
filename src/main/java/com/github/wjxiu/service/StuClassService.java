package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.StuClassDO;

import java.util.List;

/**
* @author xiu
* @description 针对表【stu_class】的数据库操作Service
* @createDate 2024-01-06 21:21:17
*/
public interface StuClassService extends IService<StuClassDO> {

    List< StuClassDO> pageList(StuClassDO stuClassDO, Integer pageNum, Integer pageSize);
    boolean updateById(StuClassDO entity) ;
}
