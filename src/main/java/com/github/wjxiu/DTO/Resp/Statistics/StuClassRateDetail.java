package com.github.wjxiu.DTO.Resp.Statistics;

import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-15 10:46
 */
@Data
public class StuClassRateDetail {
    //评价分数
    Integer rateNumber;
    /**
     * 班级内评价分数的条数
     */
    Integer totalCount;

    public StuClassRateDetail() {
    }

    public StuClassRateDetail(Integer rateNumber, Integer totalCount) {
        this.rateNumber = rateNumber;
        this.totalCount = totalCount;
    }
}
