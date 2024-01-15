package com.github.wjxiu.DTO.Resp.Statistics;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-15 15:11
 */
@Data@Builder
public class BestAndWorstRateResp {
    Integer stuClassId;
    String stuClassName;
    List<StuClassRateDetail> bestItem;
    List<StuClassRateDetail> worstItem;
}
