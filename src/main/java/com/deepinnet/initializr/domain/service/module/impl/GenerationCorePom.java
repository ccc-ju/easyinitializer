package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * core pom
 */
@Service
public class GenerationCorePom extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCorePom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "core-pom.ftl", projectInfo);

        logger.info("core创建配置文件 pom.xml {}", file.getPath());
    }

}
