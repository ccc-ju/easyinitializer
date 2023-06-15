package com.deepinnet.initializr.engine;

import com.deepinnet.initializr.context.InitializerContext;
import com.deepinnet.initializr.domain.enums.InitializerErrorCode;
import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import com.deepinnet.initializr.domain.model.InitializerEngineResult;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.exception.InitializerException;
import com.deepinnet.initializr.handler.InitializerGeneratorHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *   项目生成模板引擎实现
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */

@Service
@RequiredArgsConstructor
public class InitializerEngineImpl implements InitializerEngine, InitializingBean {

    /**
     * 默认日志文件
     */
    private static final Logger logger = LoggerFactory.getLogger(InitializerEngineImpl.class);

    /**
     * <p>
     *   从spring上下文中注入InitializerGeneratorHandler的所有实例
     * </p>
     * <p>
     *   map的key是InitializerGeneratorHandler实例bean的name，value是bean实例
     * </p>
     */
    private final Map<String, InitializerGeneratorHandler> handlers;

    /**
     * 保险操作注册的处理器
     */
    private Map<InitializerTypeEnum, InitializerGeneratorHandler> handlerMap = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        handlers.values().parallelStream().forEach(handler -> handlerMap.put(handler.supportOperation(), handler));
    }

    @Override
    public void execute(ProjectInitDTO projectInitDTO, InitializerTypeEnum initializerTypeEnum) {
        InitializerContext initializerContext = InitializerContext.getInstance();
        try {
            // 前置处理
            prepare(projectInitDTO, initializerTypeEnum);

            // 项目生成处理
            this.handlerMap.get(initializerTypeEnum).handle(projectInitDTO);

            // 结果转换
            // return getResult(projectInitDTO, null);

        } catch (IllegalArgumentException ex) {
            logger.error(getLogString("引擎执行参数异常", projectInitDTO), ex);
            throw new InitializerException(InitializerErrorCode.PARAM_ERROR.getErrorCode(), InitializerErrorCode.PARAM_ERROR.getErrorCode());
            // return getResult(projectInitDTO, ex);
        } catch (Throwable ex) {
            logger.error(getLogString("引擎执行未知异常", projectInitDTO), ex);
            throw new InitializerException(InitializerErrorCode.UNKNOWN_ERROR.getErrorCode(), InitializerErrorCode.UNKNOWN_ERROR.getErrorCode());
            // return getResult(projectInitDTO, ex);
        } finally {
            try {
                InitializerContext context = InitializerContext.getInstance();
                context.getInsEngineResult();
            } catch (Exception e) {
                logger.error(getLogString("引擎finally处理异常", projectInitDTO), e);
            }

            // 清理线程池
            initializerContext.clear();
        }
    }

    /**
     * 处理前的准备工作。
     *
     * <pre>
     * 1.构造上下文,建立处理环境
     *   上下文是线程变量，要确保里面的内容与本次请求的匹配。
     * 2.其他预处理流程:
     * </pre>
     *
     * @param projectInitDTO 项目初始化信息
     * @param typeEnum       项目生成类型
     */
    private void prepare(final ProjectInitDTO projectInitDTO, final InitializerTypeEnum typeEnum) {
        // 记录日志
        logger.info(getLogString("收到项目生成处理请求", projectInitDTO, typeEnum));

        // 构造上下文
        InitializerContext initializerContext = InitializerContext.getInstance();
        InitializerEngineResult initializerEngineResult = new InitializerEngineResult();
        initializerContext.setInsEngineResult(initializerEngineResult);

        // 填充本次请求需要的上下文模型 此代码无 fillAuctionModel
        // this.handlerMap.get(typeEnum).fillActionModel(projectInitDTO);
    }

    /**
     * 组装引擎返回结果
     * @param projectInitDTO   项目初始化信息
     * @param ex               异常
     * @return                 引擎结果
     */
    private InitializerEngineResult getResult(ProjectInitDTO projectInitDTO, Throwable ex) {

        InitializerEngineResult initializerEngineResult = new InitializerEngineResult();
        if (ex == null && initializerEngineResult.getInitializerErrorCode() == null) {
            initializerEngineResult.setSuccess(true);
        }

        setExceptionToEngineResult(initializerEngineResult, ex);

        if (!initializerEngineResult.isSuccess()) {
            logger.warn(getLogString("引擎执行出错(ERROR)", this.getErrorMsg(initializerEngineResult, ex)));
        } else {
            logger.info(getLogString("引擎执行成功,最终的模型", projectInitDTO));
        }

        return initializerEngineResult;
    }

    /**
     * 生成输出到日志的字符串。
     *
     * @param desc     日志描述信息
     * @param obj      任意个要输出到日志的参数[可空]
     * @return 输出到日志的字符串
     */
    protected String getLogString(String desc, Object... obj) {

        StringBuilder engineStringBuilder = new StringBuilder();
        engineStringBuilder.append(desc).append("[");

        if (obj != null && obj.length > 0) {
            engineStringBuilder.append("(");
            for (int i = 0; i < obj.length; i++) {
                engineStringBuilder.append(obj[i]);
                if (obj.length - 1 != i) {
                    engineStringBuilder.append(",");
                }
            }
            engineStringBuilder.append(")");
        }
        engineStringBuilder.append("].");
        return engineStringBuilder.toString();
    }

    /**
     * 设置异常信息到引擎返回结果
     *
     * @param initializerEngineResult 引擎返回结果
     * @param ex 异常
     */
    protected void setExceptionToEngineResult(InitializerEngineResult initializerEngineResult, Throwable ex) {
        if (ex instanceof InitializerException) {
            InitializerException initializerException = (InitializerException) ex;
            //设置错误参数
            initializerEngineResult.setErrorParameters(initializerException.getParameters());
            //设置统一错误码

            InitializerErrorCode initializerErrorCode = InitializerErrorCode.getByCode(initializerException.getErrorCode());
            initializerEngineResult.setInitializerErrorCode(initializerErrorCode);

            //设置自定义的错误描述
            if (initializerException.getMessage() != null) {
                initializerEngineResult.setErrorDesc(initializerException.getMessage());
            }
        } else if (ex instanceof IllegalArgumentException) {
            initializerEngineResult.setInitializerErrorCode(InitializerErrorCode.PARAM_ERROR);
            initializerEngineResult.setErrorDesc(ex.getMessage());
        } else if (ex instanceof Exception) {
            initializerEngineResult.setInitializerErrorCode(InitializerErrorCode.UNKNOWN_ERROR);
            initializerEngineResult.setErrorDesc(InitializerErrorCode.UNKNOWN_ERROR.getErrorDesc());
        }
    }

    /**
     * 获取引擎结果中的错误信息。
     *
     * @param engineResult 引擎结果
     * @return 错误信息
     */
    protected String getErrorMsg(InitializerEngineResult engineResult, Throwable ex) {
        if (ex instanceof InitializerException) {
            return ex.getMessage();
        }
        if (engineResult != null && engineResult.getInitializerErrorCode() != null) {
            return engineResult.getInitializerErrorCode().getErrorDesc();
        }
        return "";
    }
}
