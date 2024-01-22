package com.github.wjxiu.DTO.Resp.Statistics;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 教师的评价情况
 *
 * @author xiu
 * @create 2024-01-14 22:27
 */
@Data
public class TeacherRateSituationResp {
    Integer teacherId;
    String teacherName;
    Integer rateTotal;
//    已评价的
    Integer ratedCount;

//    未评价的
    Integer remainCount;
//
    Double median;

    //    Median）、众数（Mode）、平均数（Mean）
    List<Double> mode;
    Double mean;

    List<TeacherRateItemSituation> list;
}
