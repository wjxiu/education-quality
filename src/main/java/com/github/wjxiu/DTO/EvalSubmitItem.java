package com.github.wjxiu.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiu
 * @create 2024-01-11 23:01
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvalSubmitItem {
    Integer id;
    Integer evalItemId;
    Integer rate;

}
