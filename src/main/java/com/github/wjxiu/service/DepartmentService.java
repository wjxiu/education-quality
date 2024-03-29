package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.DepartmentDO;

import java.util.List;

/**
* @author xiu
* @description 针对表【department(学院表)】的数据库操作Service
* @createDate 2024-01-06 21:21:00
*/
public interface DepartmentService extends IService<DepartmentDO> {

    List<DepartmentDO> pagelist(Integer id, String name, Integer pageNum, Integer pageSize);

    List<String> getallName(String majorName);
}
