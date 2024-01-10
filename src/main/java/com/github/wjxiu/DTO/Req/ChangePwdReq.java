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
    @NotNull(message = "id不能为空")
    Integer id;
    @NotBlank
    String originalPasswd;
    @NotBlank
    String repeatedPasswd;
    @NotBlank
    String newPasswd;
}
