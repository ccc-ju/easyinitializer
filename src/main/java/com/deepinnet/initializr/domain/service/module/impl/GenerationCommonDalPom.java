package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * common-dal pom
 */
@Service
public class GenerationCommonDalPom extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCommonDalPom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "common-dal-pom.ftl", projectInfo);

        logger.info("common-dal创建配置文件 pom.xml {}", file.getPath());
    }

}
