package com.demo.littlehelper.common.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("1001", "查無會員資料"),
    DATA_NOT_FOUND("1002", "查無資料"),
    SERVICE_ERROR("1003", "執行錯誤請洽系統管理員"),
    WS_EXCHANGE_FEIGNCLIENT_DATA_NOT_FOUND("1004", "未取得匯率資料");
    
    /** 錯誤代碼 */
    private String code;
    /** 錯誤訊息 */
    private String message;
    
    public static String appendErrorMessage(ErrorCode errorcode) {
        return String.format("Error Code:%s -%s", errorcode.getCode(), errorcode.getMessage());
    }
}
