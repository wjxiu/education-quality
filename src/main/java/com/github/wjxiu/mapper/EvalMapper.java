package com.github.wjxiu.mapper;

import com.github.wjxiu.DO.EvalDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DTO.Resp.EvalRateReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiu
* @description 针对表【eval(评分选项表)】的数据库操作Mapper
* @createDate 2024-01-10 13:59:00
* @Entity generator.DO.Eval
*/
public interface EvalMapper extends BaseMapper<EvalDO> {

    List<EvalRateReq> getTeacherEvalByStuidAndTeacherId(@Param("stuId") Integer stuId, @Param("teacherId") Integer teacherId);
}




