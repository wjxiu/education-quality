package com.github.wjxiu.DTO.Req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xiu
 * @create 2024-01-08 23:24
 */
@Data
public class LoginReq {
    @NotNull
    Integer id;
    @NotBlank
    String password;
    @Range(min = 0L,max = 2L)
    Integer type;
}
