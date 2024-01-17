package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.github.wjxiu.DO.StudentDO;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.Req.StudentPageReq;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiu
* @description 针对表【student(学生表)】的数据库操作Mapper
* @createDate 2024-01-06 21:21:24
* @Entity generator.DO.StudentDO
*/
public interface StudentMapper extends BaseMapper<StudentDO> {

    List<EvalRateResp> getAllTeacher(@Param("studentId") Integer studentId);

    List<StudentDO> pageList(@Param("studentDO") StudentPageReq studentDO,
                                 @Param("pageNum")Integer pageNum,
                                 @Param("pageSize") Integer pageSize);


}




