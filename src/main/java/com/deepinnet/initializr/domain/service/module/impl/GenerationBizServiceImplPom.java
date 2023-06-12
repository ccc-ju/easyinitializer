package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * app-starter pom
 */
@Service
public class GenerationBizServiceImplPom extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationBizServiceImplPom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "biz-service-impl-pom.ftl", projectInfo);

        logger.info("创建配置文件 pom.xml {}", file.getPath());
    }

}
