package com.github.wjxiu.utils;


import org.apache.commons.math3.stat.StatUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xiu
 * @create 2024-01-14 22:57
 */
public class StatisticsUtil {
    // 计算平均数
    public static double calculateMean(List<Double> list) {
        Double v = list.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(v));

    }
    public static double calculateMean(double[] list) {
        List<Double> list1 = Arrays.stream(list).boxed().toList();
        return calculateMean(list1);
    }

    // 计算众数
    public static List<Double> calculateMode(List<Double> list) {
        double[] array = list.stream().mapToDouble(Double::doubleValue).toArray();
        double[] mode = StatUtils.mode(array);
        return Arrays.stream(StatUtils.mode(mode)).boxed().toList();
    }
    public static List<Double> calculateMode(double[] list) {
        List<Double> list1 = Arrays.stream(list).boxed().toList();
        return calculateMode(list1);
    }


        // 计算中位数
    public static double calculateMedian(List<Double> list) {
//        不可变list，排序报错
        Collections.sort( new ArrayList<>(list));
        int size = list.size();
        if (size % 2 == 0) {
            // 偶数个元素取中间两个的平均值
            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0;
        } else {
            // 奇数个元素取中间值
            return list.get(size / 2);
        }
    }
    public static double calculateMedian(double[] list){
        List<Double> list1 = Arrays.stream(list).boxed().toList();
        return calculateMedian(list1);
    }
}
