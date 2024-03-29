package com.github.wjxiu.service;

import com.github.wjxiu.DTO.Resp.Statistics.ClassRateSituationResp;
import com.github.wjxiu.DTO.Resp.Statistics.TeacherRateSituationResp;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-14 22:24
 */
public interface StatisticsService {
    List<TeacherRateSituationResp> getStudentRateSituation(List<Integer> teacherId);

    List<String> getStudentRemainStuClassRate(List<Integer> studentIds);
    /**
     * 查看某个班级的评分条数
     * @param stuClassIds
     * @return
     */
    List<ClassRateSituationResp> getStuClassRateSituation(List<Integer> stuClassIds);

}
