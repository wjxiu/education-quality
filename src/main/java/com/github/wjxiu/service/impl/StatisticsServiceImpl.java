package com.github.wjxiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wjxiu.DO.*;
import com.github.wjxiu.DTO.Resp.Statistics.*;
import com.github.wjxiu.service.*;
import com.github.wjxiu.utils.StatisticsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiu
 * @create 2024-01-14 22:24
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    final StudentEvalService studentEvalService;
    final EvalService evalService;
    final StudentCourseClassTeacherService studentCourseClassTeacherService;
    final TeacherService teacherService;
    final StuClassService stuClassService;

    //todo 年份可选
    @Override
    public List<TeacherRateSituationResp> getStudentRateSituation(List<Integer> teacherIds) {
        List<StudentEvalDO> list = studentEvalService.list(new LambdaQueryWrapper<StudentEvalDO>()
                .in(teacherIds != null && !teacherIds.isEmpty(), StudentEvalDO::getTeacherId, teacherIds).
                likeRight(StudentEvalDO::getCreateTime, LocalDate.now().getYear()));
        Map<Integer, String> teacherIdToName = teacherService.list(new LambdaQueryWrapper<TeacherDO>()
                        .in(teacherIds != null && !teacherIds.isEmpty(), TeacherDO::getId, teacherIds))
                .stream()
                .collect(Collectors.toMap(TeacherDO::getId, TeacherDO::getRealName));
        Map<Integer, List<StudentEvalDO>> teacherIdToList = list.stream()
                .collect(Collectors.groupingBy(StudentEvalDO::getTeacherId));
        Map<Integer, String> idtoitem = evalService.list(null).stream().collect(Collectors.toMap(EvalDO::getId, EvalDO::getEvalItem));
        ArrayList<TeacherRateSituationResp> resp = new ArrayList<>();
        for (Map.Entry<Integer, List<StudentEvalDO>> teacherInfo : teacherIdToList.entrySet()) {
            List<StudentEvalDO> studentEvalDOS = teacherInfo.getValue();
            List<Double> ratelist = studentEvalDOS.stream().map(item -> item.getRate().doubleValue()).toList();
            Map<Integer, List<StudentEvalDO>> evalIdToList = studentEvalDOS
                    .stream()
                    .collect(Collectors.groupingBy(StudentEvalDO::getEvalId));
            ArrayList<TeacherRateItemSituation> items = new ArrayList<>();
            for (Map.Entry<Integer, List<StudentEvalDO>> entry : evalIdToList.entrySet()) {
                List<Double> rateItemList = entry.getValue().
                        stream()
                        .map(item -> item.getRate().doubleValue()).toList();
                List<Double> mode = StatisticsUtil.calculateMode(rateItemList);
                double median = StatisticsUtil.calculateMedian(rateItemList);
                double mean = StatisticsUtil.calculateMean(rateItemList);
                items.add(new TeacherRateItemSituation(idtoitem.get(entry.getKey()), median, mean, mode));
            }
            List<Double> mode = StatisticsUtil.calculateMode(ratelist);
            double median = StatisticsUtil.calculateMedian(ratelist);
            double mean1 = StatisticsUtil.calculateMean(ratelist);
            TeacherRateSituationResp resp1 = new TeacherRateSituationResp();
            resp1.setList(items);
            resp1.setTeacherName(teacherIdToName.get(teacherInfo.getKey()));
            resp1.setTeacherId(teacherInfo.getKey());
            resp1.setMode(mode);
            resp1.setMedian(median);
            resp1.setMean(mean1);
            resp.add(resp1);
        }
        return resp;
    }

    @Override
    public List<String> getStudentRemainStuClassRate(List<Integer> studentIds) {
        long evalCount = evalService.count();
        List<StudentCourseClassTeacherDO> list = studentCourseClassTeacherService
                .list(new LambdaQueryWrapper<StudentCourseClassTeacherDO>()
                .in(studentIds != null && !studentIds.isEmpty(), StudentCourseClassTeacherDO::getStudentId, studentIds)
                .likeRight(StudentCourseClassTeacherDO::getCreateTime, LocalDate.now().getYear()));
        List<StudentEvalDO> studentEvalDOS = studentEvalService.list(new LambdaQueryWrapper<StudentEvalDO>()
                .in(studentIds != null && !studentIds.isEmpty(), StudentEvalDO::getStuId, studentIds)
                .likeRight(StudentEvalDO::getCreateTime, LocalDate.now().getYear()));
        Map<Integer, List<StudentEvalDO>> collect1 = studentEvalDOS
                .stream()
                .collect(Collectors.groupingBy(StudentEvalDO::getStuClassId));
        ArrayList<String> res = new ArrayList<>();
        for (StudentCourseClassTeacherDO studentCourseClassTeacherDO : list) {
            Integer stuClassId = studentCourseClassTeacherDO.getStuClassId();
            List<StudentEvalDO> studentEvalDOS1 = collect1.get(stuClassId);
            if (studentEvalDOS1 == null || studentEvalDOS1
                    .stream()
                    .map(StudentEvalDO::getRate)
                    .filter(i -> i > 0)
                    .count() < evalCount) {
                res.add(studentCourseClassTeacherDO.getStuClassName());
            }
        }
        return res;
    }

    /**
     * 查看某个班级的评分条数
     *
     * @param stuClassId
     * @return
     */
    @Override
    public List<ClassRateSituationResp> getStuClassRateSituation(List<Integer> stuClassId) {
//        查看班级的评分
        List<StudentEvalDO> list1 = studentEvalService
                .list(new LambdaQueryWrapper<StudentEvalDO>()
                        .in(stuClassId != null && !stuClassId.isEmpty(), StudentEvalDO::getStuClassId, stuClassId));
        Map<Integer, List<StudentEvalDO>> collect = list1.stream().collect(Collectors.groupingBy(StudentEvalDO::getStuClassId));
        ArrayList<ClassRateSituationResp> res = new ArrayList<>();
        for (Map.Entry<Integer, List<StudentEvalDO>> entry : collect.entrySet()) {
            double[] array = entry.getValue().stream().mapToDouble(StudentEvalDO::getRate).toArray();
            StuClassDO byId = stuClassService.getById(entry.getKey());
            ClassRateSituationResp build = ClassRateSituationResp.builder()
                    .stuClassName(byId.getName()).stuClassId(entry.getKey())
                    .teacherName(byId.getTeacherName()).mean(StatisticsUtil.calculateMean(array))
                    .mode(StatisticsUtil.calculateMode(array)).median(StatisticsUtil.calculateMedian(array)).build();
            Map<Double, Long> rateCount = Arrays.stream(array).boxed().collect(Collectors.groupingBy(
                    rate -> rate, // 分组条件，即数字本身
                    Collectors.counting() // 统计每个数字的个数
            ));
            ArrayList<StuClassRateDetail> stuClassRateDetails = new ArrayList<>();
            for (Map.Entry<Double, Long> doubleLongEntry : rateCount.entrySet()) {
                stuClassRateDetails.add(new StuClassRateDetail(doubleLongEntry.getKey().intValue(), doubleLongEntry.getValue().intValue()));
            }
            build.setRateDetails(stuClassRateDetails);
            res.add(build);
        }
//        查看对应的评价条数
        return res;
    }

}
