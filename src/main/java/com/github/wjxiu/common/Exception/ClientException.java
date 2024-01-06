package com.github.wjxiu.common.Exception;


import com.github.wjxiu.common.ErrorCode.BaseErrorCode;
import com.github.wjxiu.common.ErrorCode.IErrorCode;

/**
 * @author xiu
 * @create 2023-11-20 15:24
 */

public class ClientException extends AbstractException{

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }
    public ClientException( IErrorCode errorCode) {
        super(null, null, errorCode);
    }
    public ClientException(String message) {
        super(message, null, BaseErrorCode.CLIENT_ERROR);
    }
    public ClientException(String message, IErrorCode errorCode) {
        super(message, null, errorCode);
    }

}
