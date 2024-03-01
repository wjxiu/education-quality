package com.github.wjxiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wjxiu.DO.MajorDO;
import com.github.wjxiu.DO.StudentReviewsDO;
import com.github.wjxiu.DTO.Resp.StudentReviewsResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author xiu
* @description 针对表【student_reviews(学生对老师的评价)】的数据库操作Mapper
* @createDate 2024-02-29 17:35:49
* @Entity generator.DO.StudentReviews
*/
public interface StudentReviewsMapper extends BaseMapper<StudentReviewsDO> {

    List<StudentReviewsResp> pageList(@Param("studentReviewsDO") StudentReviewsDO studentReviewsDO,
                                      @Param("pageNum")Integer pageNum,
                                      @Param("pageSize")Integer pageSize);
}




