package com.deepinnet.initializr.domain.enums;

/**
 * <p>
 *  异常枚举
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */
public enum InitializerErrorCode {

    GENERATION_ERROR("0001", "项目生成失败"),

    PARAM_ERROR("0002", "参数异常"),

    UNKNOWN_ERROR("0004", "未知异常"),

    DATA_BASE_ERROR("0005", "数据库连接异常"),

    DOWN_LOAD_ERROR("0006", "下载异常"),

    ;

    InitializerErrorCode (String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public static InitializerErrorCode getByDesc(String desc) {
        for (InitializerErrorCode initializerErrorCode : InitializerErrorCode.values()) {
            if (initializerErrorCode.getErrorDesc().equals(desc)) {
                return initializerErrorCode;
            }
        }
        return null;
    }

    public static InitializerErrorCode getByCode(String errorCode) {
        for (InitializerErrorCode initializerErrorCode : InitializerErrorCode.values()) {
            if (initializerErrorCode.getErrorCode().equals(errorCode)) {
                return initializerErrorCode;
            }
        }
        return null;
    }

    private String errorCode;

    private String errorDesc;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
