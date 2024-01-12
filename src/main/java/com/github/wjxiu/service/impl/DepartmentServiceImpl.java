package com.github.wjxiu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wjxiu.DO.DepartmentDO;
import com.github.wjxiu.mapper.DepartmentMapper;
import com.github.wjxiu.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiu
* @description 针对表【department(学院表)】的数据库操作Service实现
* @createDate 2024-01-06 21:21:00
*/
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentDO>
    implements DepartmentService {
    final DepartmentMapper departmentMapper;
    @Override
    public List<DepartmentDO> pagelist(Integer id, String name, Integer pageNum, Integer pageSize) {
       return  departmentMapper.pagelist(id,name,pageNum,pageSize);
    }
}




