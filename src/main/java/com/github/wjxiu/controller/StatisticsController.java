package com.github.wjxiu.controller;

import com.github.wjxiu.DTO.Resp.Statistics.ClassRateSituationResp;
import com.github.wjxiu.DTO.Resp.Statistics.TeacherRateSituationResp;
import com.github.wjxiu.common.R;
import com.github.wjxiu.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计控制器
 * @author xiu
 * @create 2024-01-14 22:11
 */
@RestController
@RequestMapping("statistics")
@RequiredArgsConstructor
public class StatisticsController {
    final StatisticsService statisticsService;
//    都有年份统计
//    教师查看多少学生未评价，已经评价
//    学生查看需要评价多少教师
//    教师查看自己的评分情况
//    管理员查看评分情况

    /**
     * 查看某个教师所属学生的评分情况
     * @return
     */
    @GetMapping(value = {"/StudentRateSituation","/StudentRateSituation/{teacherIds}"})
    public R<List<TeacherRateSituationResp>> getStudentRateSituation(@PathVariable(value = "teacherIds",required = false) List<Integer> teacherIds){
       return R.success(statisticsService.getStudentRateSituation(teacherIds));
    }


    /**
     * 查看学生未完成评价的课程
     * @param studentIds
     * @return
     */
    @GetMapping(value = {"/StudentRemainStuClassRate/{studentIds}"})
    public R getStudentRemainStuClassRate(@PathVariable List<Integer> studentIds){
        return R.success(statisticsService.getStudentRemainStuClassRate(studentIds));
    }

    /**
     * 查看某个班级的评分条数
     * @param stuClassId
     * @return
     */
    @GetMapping(value = {"StuClassRateSituation/{stuClassId}","/StuClassRateSituation"})
    public R<List<ClassRateSituationResp>> StuClassRateSituation(@PathVariable(required = false) List<Integer> stuClassId){
        return R.success(statisticsService.getStuClassRateSituation(stuClassId));
    }
}
