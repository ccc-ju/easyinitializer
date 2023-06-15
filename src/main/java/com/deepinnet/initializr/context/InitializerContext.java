package com.deepinnet.initializr.context;

import com.deepinnet.initializr.domain.model.InitializerEngineResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  线程上下文
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */
public class InitializerContext {

    /**
     * 线程实例
     */
    private static ThreadLocal<InitializerContext> threadLocalContext = new ThreadLocal<>();

    /**
     * 上下文属性
     */
    private Map<String, Object> properties = new ConcurrentHashMap<>();


    /**
     * 清理线程上下文
     */
    public void clear() {
        threadLocalContext.remove();
    }

    /**
     * 获取实例
     */
    public static synchronized InitializerContext getInstance() {
        if (threadLocalContext.get() != null) {
            return threadLocalContext.get();
        }
        InitializerContext context = new InitializerContext();
        threadLocalContext.set(context);
        return context;
    }

    /**
     * 增加属性
     *
     * @param propertyName  属性名
     * @param propertyValue 属性值
     */
    private void addProperty(String propertyName, Object propertyValue) {
        this.properties.put(propertyName, propertyValue);
    }

    /**
     * 获取属性值
     *
     * @param propertyName 属性名
     */
    private Object getProperty(String propertyName) {
        return this.properties.get(propertyName);
    }

    /**
     * 获取引擎结果。
     * <p>
     * 引擎结果
     */
    public InitializerEngineResult getInsEngineResult() {
        return (InitializerEngineResult) this.getProperty("initializerEngineResult");
    }

    /**
     * 设置引擎结果。
     *
     * @param initializerEngineResult 引擎结果
     */
    public void setInsEngineResult(InitializerEngineResult initializerEngineResult) {
        this.addProperty("initializerEngineResult", initializerEngineResult);
    }

}
