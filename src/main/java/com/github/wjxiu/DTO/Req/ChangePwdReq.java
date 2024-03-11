package com.github.wjxiu.DTO.Req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xiu
 * @create 2024-01-08 10:45
 */
@Data
public class ChangePwdReq {
    Integer id;
    @NotBlank
    String oldPassword;
    @NotBlank
    String newPassword;
    @NotBlank
    String confirmPassword;
    Integer type;
}
