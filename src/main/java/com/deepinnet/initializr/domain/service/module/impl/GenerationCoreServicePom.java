package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * core-service pom
 */
@Service
public class GenerationCoreServicePom extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCoreServicePom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "core-service-pom.ftl", projectInfo);

        logger.info("core-service创建配置文件 pom.xml {}", file.getPath());
    }

}
