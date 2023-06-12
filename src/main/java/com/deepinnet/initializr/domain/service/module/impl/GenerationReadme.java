package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 生成Readme
 */
@Service
public class GenerationReadme extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationParentPom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "README.md"
        );

        // 写入文件
        super.writeFile(file, "README.ftl", projectInfo);

        logger.info("创建说明文件 README.ftl");
    }

}
