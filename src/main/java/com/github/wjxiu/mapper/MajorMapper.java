package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.MajorDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【major】的数据库操作Mapper
 * @createDate 2024-01-06 21:21:08
 * @Entity generator.DO.Major
 */
public interface MajorMapper extends BaseMapper<MajorDO> {

    List<MajorDO> pageList(@Param("majorDO") MajorDO majorDO,
                           @Param("pageNum") Integer pageNum,
                           @Param("pageSize") Integer pageSize);

}




