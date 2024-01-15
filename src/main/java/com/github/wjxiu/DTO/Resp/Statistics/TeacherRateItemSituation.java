package com.github.wjxiu.DTO.Resp.Statistics;

import lombok.Data;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-14 22:36
 */
@Data
public class TeacherRateItemSituation {

    String  evalItem;
    Double median;
    //    Median）、众数（Mode）、平均数（Mean）
    List<Double> mode;
    Double mean;

    public TeacherRateItemSituation(String evalItem, Double median,  Double mean,List<Double> mode) {
        this.evalItem = evalItem;
        this.median = median;
        this.mode = mode;
        this.mean = mean;
    }

    public TeacherRateItemSituation() {
    }
}
