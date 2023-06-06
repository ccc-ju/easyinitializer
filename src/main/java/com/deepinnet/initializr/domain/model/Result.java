package com.deepinnet.initializr.domain.model;

import java.io.Serializable;

/**
 * <p>
 *  返回结果
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */
public class Result<T> implements Serializable {

    /**
     * SID
     */
    private static final long serialVersionUID = -9015635270304492869L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 错误码名称
     */
    private String errorCode;

    /**
     * 错误码描述, 主要针对保险产生的统一错误码的描述
     */
    private String errorDesc;

    /**
     * exceptionType
     * 由于facade不抛出异常通过InsException把异常扔出
     * 需增加exceptionType去打印异常类型
     */
    private String exceptionType;

    /**
     * 业务数据
     */
    private T data;

    public Result() {
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, String errorCode, String errorDesc, T model) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.data = model;
    }

    public Result(boolean success, String errorCode, String errorDesc) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public Result(boolean success, String errorCode, String errorDesc, String exceptionType) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.exceptionType = exceptionType;
    }

    /**
     * 成功结果构造
     *
     * @param data
     * @param <T>
     */
    public static <T> Result<T> success(T data) {
        return new Result(true, data);
    }

    /**
     * 失败结果构造
     *
     * @param errorCode
     * @param errorDesc
     */
    public static <T> Result<T> fail(String errorCode, String errorDesc) {
        return new Result<>(false, errorCode, errorDesc);
    }

    /**
     * 失败结果构造
     *
     * @param errorCode
     * @param errorDesc
     */
    public static <T> Result<T> fail(String errorCode, String errorDesc, String exceptionType) {
        return new Result<>(false, errorCode, errorDesc, exceptionType);
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property <tt>errorDesc</tt>.
     *
     * @return property value of errorDesc
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * Setter method for property <tt>errorDesc</tt>.
     *
     * @param errorDesc value to be assigned to property errorDesc
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                ", exceptionType='" + exceptionType + '\'' +
                ", data=" + data +
                '}';
    }
}
