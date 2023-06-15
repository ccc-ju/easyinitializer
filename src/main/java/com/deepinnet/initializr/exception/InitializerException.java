package com.deepinnet.initializr.exception;

import java.util.HashMap;
import java.util.Map;

public class InitializerException extends RuntimeException {

    private static final long serialVersionUID = 7584404361286515143L;

    /**
     * 错误码
     */
    protected String errorCode = null;

    /**
     * 异常信息
     */
    protected String message = null;

    /**
     * 错误时相关参数
     */
    protected Map<String, Object> parameters;

    /**
     * 保险异常构造函数。
     *
     * @param espErrorCode 内部错误码
     */
    public InitializerException(String espErrorCode) {
        this(espErrorCode, null, null, null);
    }

    /**
     * 保险异常构造函数。
     *
     * <p>保险内部异常时使用
     *
     * @param espErrorCode 内部错误码
     * @param message      异常信息
     */
    public InitializerException(String espErrorCode, String message) {
        this(espErrorCode, message, null, null);
    }


    /**
     * 保险异常构造函数。
     *
     * <p>保险内部异常时使用
     *
     * @param espErrorCode 内部错误码
     * @param message      异常信息
     * @param parameters   错误时相关参数
     */
    public InitializerException(String espErrorCode, String message, Map<String, Object> parameters) {
        this(espErrorCode, message, parameters, null);
    }

    /**
     * 保险异常构造函数。
     *
     * @param espErrorCode 内部错误码
     * @param message      异常信息
     * @param cause
     */
    public InitializerException(String espErrorCode, String message, Throwable cause) {
        this(espErrorCode, message, null, cause);
    }

    /**
     * 保险异常构造函数。
     *
     * @param espErrorCode
     * @param message
     * @param parameters
     * @param cause
     */
    public InitializerException(String espErrorCode, String message, Map<String, Object> parameters,
                                Throwable cause) {
        super(message, cause);
        this.errorCode = espErrorCode;
        this.message = message;
        this.parameters = parameters;
    }

    /**
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Getter method for property <tt>paramters</tt>.
     *
     * @return property value of paramters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Setter method for property <tt>paramters</tt>.
     *
     * @param parameters value to be assigned to property paramters
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 增加扩展信息
     *
     * @param key
     * @param value
     * @return 自身
     */
    public InitializerException addExtInfo(String key, String value) {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        parameters.put(key, value);

        return this;
    }

    /**
     * Getter method for property <tt>espErrorCode</tt>.
     *
     * @return property value of espErrorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>espErrorCode</tt>.
     *
     * @param espErrorCode value to be assigned to property espErrorCode
     */
    public void setString(String espErrorCode) {
        this.errorCode = espErrorCode;
    }
}