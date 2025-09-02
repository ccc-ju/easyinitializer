package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目common模块生成器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class GenerationSimpleCommon extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationSimpleCommon.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        String moduleName = projectInfo.getName() + "-common";
        File moduleDir = new File(projectsRoot, moduleName);
        if (!moduleDir.exists()) {
            moduleDir.mkdirs();
        }

        // 创建pom.xml
        File pomFile = new File(moduleDir, "pom.xml");
        super.writeFile(pomFile, "simple-common-pom.ftl", projectInfo);

        // 创建目录结构
        String packagePath = projectInfo.getGroupId().replaceAll("\\.", "/") + "/";
        File srcMainJavaDir = new File(moduleDir, "src/main/java/" + packagePath + "/common");
        if (!srcMainJavaDir.exists()) {
            srcMainJavaDir.mkdirs();
        }

        // 创建util目录
        File utilDir = new File(srcMainJavaDir, "util");
        if (!utilDir.exists()) {
            utilDir.mkdirs();
        }

        // 创建config目录
        File configDir = new File(srcMainJavaDir, "config");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        // 创建package-info.java
        File packageInfoFile = new File(srcMainJavaDir, "package-info.java");
        String packageName = projectInfo.getGroupId() + "." + projectInfo.getArtifactId().replaceAll("-", "") + ".common";
        super.writeFile(packageInfoFile, "package-info.ftl", new ApplicationInfo(packageName));

        logger.info("创建普通项目common模块 {}", moduleDir.getPath());
    }
}