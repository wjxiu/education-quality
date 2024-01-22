package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.TeacherDO;
import com.github.wjxiu.DTO.Req.TeacherPageReq;
import com.github.wjxiu.DTO.Resp.TeacherPageResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【teacher(教师表)】的数据库操作Mapper
 * @createDate 2024-01-06 21:51:51
 * @Entity generator.DO.TeacherDO
 */
public interface TeacherMapper extends BaseMapper<TeacherDO> {

   //todo 改成单表
//   public List<TeacherPageResp> selectTeacherList(@Param("teacher") TeacherPageReq teacher,
//                                                  @Param("pageNum")Integer pageNum,
//                                                  @Param("pageSize") Integer pageSize);

   List<TeacherDO> selectTeacherList1(@Param("teacher") TeacherPageReq teacher,
                                            @Param("pageNum")Integer pageNum,
                                            @Param("pageSize") Integer pageSize);
}




