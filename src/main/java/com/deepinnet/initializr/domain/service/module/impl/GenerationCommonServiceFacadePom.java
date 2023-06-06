package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * common-service-facade pom
 */
@Service
public class GenerationCommonServiceFacadePom extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCommonServiceFacadePom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "common-service-facade-pom.ftl", projectInfo);

        logger.info("common-service-facade创建配置文件 pom.xml {}", file.getPath());
    }

}
