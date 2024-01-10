package com.github.wjxiu.common;

import com.github.wjxiu.common.ErrorCode.BaseErrorCode;
import com.github.wjxiu.common.Exception.AbstractException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author xiu
 * @create 2023-11-20 15:15
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {


    /**
     * 构造成功响应
     */
    public static R<Void> success() {
        return new R<Void>()
                .setCode(R.SUCCESS_CODE);
    }

    /**
     * 构造带返回数据的成功响应
     */
    public static <T> R<T> success(T data){
        return new R<T>().setData(data).setCode(R.SUCCESS_CODE);
    }

    /**
     * 构建服务端失败响应
     */
    public static R<Void> failure() {
        return new R<Void>()
                .setCode(BaseErrorCode.SERVICE_ERROR.code())
                .setMessage(BaseErrorCode.SERVICE_ERROR.message());
    }

    /**
     * 通过 {@link AbstractException} 构建失败响应
     */
    protected static R<Void> failure(AbstractException abstractException) {
        String errorCode = Optional.ofNullable(abstractException.getErrorCode())
                .orElse(BaseErrorCode.SERVICE_ERROR.code());
        String errorMessage = Optional.ofNullable(abstractException.getErrorMessage())
                .orElse(BaseErrorCode.SERVICE_ERROR.message());
        return new R<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }

    /**
     * 通过 errorCode、errorMessage 构建失败响应
     */
     public static R<Void> failure(String errorMessage, String errorCode) {
        return new R<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }


    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    /**
     * 正确返回码
     */
    public static final String SUCCESS_CODE = "200";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求ID
     */
    private String requestId;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
