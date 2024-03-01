package com.github.wjxiu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.wjxiu.DO.StudentReviewsDO;
import com.github.wjxiu.DTO.Resp.StudentReviewsResp;

import java.util.List;

/**
 * @author xiu
 * @create 2024-02-29 18:13
 */
public interface StudentReviewsService extends IService<StudentReviewsDO> {
    List<? extends StudentReviewsResp> viewOfTeacher(Integer pageNum, Integer pageSize);

    List<? extends StudentReviewsResp> pageList(StudentReviewsDO studentReviewsDO, Integer pageNum, Integer pageSize);

    List<StudentReviewsResp> commentOfTeacher(Integer stuClassId, Integer courseId);
}
