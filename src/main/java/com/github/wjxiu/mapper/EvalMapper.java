package com.github.wjxiu.mapper;

import com.github.wjxiu.DO.EvalDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.StuClassDO;
import com.github.wjxiu.DTO.Resp.EvalRateResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiu
* @description 针对表【eval(评分选项表)】的数据库操作Mapper
* @createDate 2024-01-10 13:59:00
* @Entity generator.DO.Eval
*/
public interface EvalMapper extends BaseMapper<EvalDO> {

    List<EvalRateResp> getTeacherEvalByStuidAndTeacherId(@Param("stuId") Integer stuId, @Param("teacherId") Integer teacherId);

    List<EvalDO> pagelist(@Param("evalItemName") String evalItemName,
                          @Param("id") Integer id,
                          @Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);


    /**
    * @author xiu
    * @description 针对表【stu_class(这个不是关系表，为了避开java关键字)】的数据库操作Mapper
    * @createDate 2024-01-14 00:08:53
    * @Entity com.github.wjxiu.DO.StuClassDO
    */
    interface StuClassMapper extends BaseMapper<StuClassDO> {

    }
}




