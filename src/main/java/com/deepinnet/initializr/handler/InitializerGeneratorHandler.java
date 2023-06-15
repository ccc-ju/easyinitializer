package com.deepinnet.initializr.handler;

import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import com.deepinnet.initializr.dto.ProjectInitDTO;

/**
 * <p>
 *  项目生成处理器
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */
public interface InitializerGeneratorHandler {

    /**
     * 当前处理器支持的类型
     */
    InitializerTypeEnum supportOperation();

    /**
     * 执行处理
     * @param projectInitDTO 项目初始化信息
     */
    void handle(ProjectInitDTO projectInitDTO);

}
