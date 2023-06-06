package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * GenerationKnife4jConfiguration 生成
 */
@Service
public class GenerationKnife4jConfiguration extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationKnife4jConfiguration.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        ApplicationInfo applicationInfo = new ApplicationInfo(
                projectInfo.getGroupId(),
                null,
                projectInfo.getArtifactId()
        );

        String packagePath = applicationInfo.getPackageName().replace(".", "/") + "/";

        File knife4JConfigurationFile = new File(projectsRoot + "/src/main/java/" + packagePath + "/config",
                "Knife4jConfiguration.java");

        // 写入文件
        super.writeFile(knife4JConfigurationFile, "knife4j-configuration.ftl", applicationInfo);

        logger.info("创建Knife4jConfiguration.java {}", knife4JConfigurationFile.getPath());
    }

}
