package com.deepinnet.initializr.domain.model;

import com.deepinnet.initializr.domain.enums.InitializerErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  引擎返回结果
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */

@Data
public class InitializerEngineResult implements Serializable {

    private static final long serialVersionUID = 8284846284542168289L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 错误码
     */
    private InitializerErrorCode initializerErrorCode;

    /**
     * 保险内部错误描述, 主要针对保险产生的统一错误码的描述
     */
    private String errorDesc;

    /**
     * 保险模型
     */
    private ProjectInfo projectInfo;

    /**
     * 结果参数
     */
    private Map<String, Object> errorParameters;

    /**
     * 操作结果参数
     */
    private Map<String, Object> resultExtInfo = new HashMap<String, Object>();

    /**
     * 增加扩展信息。
     *
     * @param key   扩展信息key
     * @param value 扩展信息值
     */
    public void addResultExtInfo(String key, Object value) {
        if (resultExtInfo == null) {
            resultExtInfo = new HashMap<>();
        }
        resultExtInfo.put(key, value);
    }

}
