package com.deepinnet.initializr.engine;

import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import com.deepinnet.initializr.dto.ProjectInitDTO;

/**
 * <p>
 *  项目生成引擎模板
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */
public interface InitializerEngine {

    /**
     * 项目生成
     * @param projectInitDTO 项目初始化信息
     * @param initializerTypeEnum 项目初始化类型
     */
    void execute(ProjectInitDTO projectInitDTO, InitializerTypeEnum initializerTypeEnum);

}
