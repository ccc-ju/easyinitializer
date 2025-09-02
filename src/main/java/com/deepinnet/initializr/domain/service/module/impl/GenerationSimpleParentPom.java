package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目父pom生成器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class GenerationSimpleParentPom extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationSimpleParentPom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                "pom.xml"
        );

        // 写入文件
        super.writeFile(file, "simple-parent-pom.ftl", projectInfo);

        logger.info("创建普通项目父配置文件 pom.xml {}", file.getPath());
    }
}