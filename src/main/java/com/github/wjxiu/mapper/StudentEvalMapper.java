package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.StudentEvalDO;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiu
 * @description 针对表【student_eval】的数据库操作Mapper
 * @createDate 2024-01-11 21:56:30
 * @Entity com.github.wjxiu.DO.StudentEvalDO
 */
public interface StudentEvalMapper extends BaseMapper<StudentEvalDO> {

    List<EvalRateResp> getStudentRatePage(@Param("resp") EvalRateResp evalRateResp,@Param("pageNum")Integer pageNum,@Param("pageSize") Integer pageSize);
}