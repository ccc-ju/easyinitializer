package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目api-model模块生成器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class GenerationSimpleApiModel extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationSimpleApiModel.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        String moduleName = projectInfo.getName() + "-api-model";
        File moduleDir = new File(projectsRoot, moduleName);
        if (!moduleDir.exists()) {
            moduleDir.mkdirs();
        }

        // 创建pom.xml
        File pomFile = new File(moduleDir, "pom.xml");
        super.writeFile(pomFile, "simple-api-model-pom.ftl", projectInfo);

        // 创建目录结构
        String packagePath = projectInfo.getGroupId().replaceAll("\\.", "/") + "/";

        logger.info("groupId:{}, artifactId:{}", projectInfo.getGroupId().replaceAll("\\.", "/"), projectInfo.getArtifactId().replaceAll("-", ""));
        File srcMainJavaDir = new File(moduleDir, "src/main/java/" + packagePath);
        if (!srcMainJavaDir.exists()) {
            srcMainJavaDir.mkdirs();
        }

        // 创建package-info.java
        File packageInfoFile = new File(srcMainJavaDir, "package-info.java");
        String packageName = projectInfo.getGroupId() + "." + projectInfo.getArtifactId().replaceAll("-", "") + ".api.model";
        super.writeFile(packageInfoFile, "package-info.ftl", new ApplicationInfo(packageName));

        logger.info("创建普通项目api-model模块 {}", moduleDir.getPath());
    }
}