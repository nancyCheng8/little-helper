package com.demo.littlehelper.common.exceptionhandler.exceptions;

import com.demo.littlehelper.common.exceptionhandler.ErrorCode;

public class ServiceException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ServiceException(ErrorCode errorCode) {
        super(ErrorCode.appendErrorMessage(errorCode));
    }
}
