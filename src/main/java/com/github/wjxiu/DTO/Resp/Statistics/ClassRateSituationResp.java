package com.github.wjxiu.DTO.Resp.Statistics;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author xiu
 * @create 2024-01-15 10:43
 */
@Data@Builder
public class ClassRateSituationResp {
    Integer stuClassId;
    String teacherName;
    String stuClassName;
    Double median;

    //  中位数Median、众数（Mode）、平均数（Mean）
    List<Double> mode;
    Double mean;
    List<StuClassRateDetail> rateDetails;
}
