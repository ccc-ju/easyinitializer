package com.deepinnet.initializr.exception;

import com.deepinnet.initializr.domain.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * @author chenjiaju
 * @version 2022/11/10 17:33
 */

@RestControllerAdvice
public class GlobalRestExceptionInterceptor {

    private final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionInterceptor.class);

    @ExceptionHandler({MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
    public Result<Object> missingParamException(Exception ex){
        logger.error(ex.getMessage(), ex);
        return Result.fail("0002", "参数异常");
    }

    /**
     * 拦截业务层面异常并转成EspResult
     * @param e 异常
     * @return EspResult
     */
    @ExceptionHandler(InitializerException.class)
    public Result<Object> espException(InitializerException e){
        logger.error(e.getMessage(), e);
        return Result.fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> exception(Exception e) {
        logger.error("未知异常", e);
        return Result.fail("0004", "未知异常");
    }

}
