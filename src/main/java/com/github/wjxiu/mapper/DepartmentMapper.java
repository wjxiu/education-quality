package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.DepartmentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【department(学院表)】的数据库操作Mapper
 * @createDate 2024-01-06 21:21:00
 * @Entity generator.DO.Department
 */
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    List<DepartmentDO> pagelist(@Param("id") Integer id,
                                @Param("name") String name,
                                @Param("pageNum") Integer pageNum,
                                @Param("pageSize") Integer pageSize);
}




