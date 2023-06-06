package com.deepinnet.initializr.application;

import com.deepinnet.initializr.domain.model.ProjectInfo;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
public interface IProjectGenerator {

    void generator(ProjectInfo projectInfo) throws Exception;

}
