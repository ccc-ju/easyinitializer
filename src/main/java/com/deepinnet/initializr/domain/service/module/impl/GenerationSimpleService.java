package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目service模块生成器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class GenerationSimpleService extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationSimpleService.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        String moduleName = projectInfo.getName() + "-service";
        File moduleDir = new File(projectsRoot, moduleName);
        if (!moduleDir.exists()) {
            moduleDir.mkdirs();
        }

        // 创建pom.xml
        File pomFile = new File(moduleDir, "pom.xml");
        super.writeFile(pomFile, "simple-service-pom.ftl", projectInfo);

        // 创建目录结构
        String packagePath = projectInfo.getGroupId().replaceAll("\\.", "/") + "/";
        File srcMainJavaDir = new File(moduleDir, "src/main/java/" + packagePath + "/service");
        if (!srcMainJavaDir.exists()) {
            srcMainJavaDir.mkdirs();
        }

        // 创建impl目录
        File implDir = new File(srcMainJavaDir, "impl");
        if (!implDir.exists()) {
            implDir.mkdirs();
        }

        // 创建package-info.java
        File packageInfoFile = new File(srcMainJavaDir, "package-info.java");
        String packageName = projectInfo.getGroupId() + "." + projectInfo.getArtifactId().replaceAll("-", "") + ".service";
        super.writeFile(packageInfoFile, "package-info.ftl", new ApplicationInfo(packageName));

        logger.info("创建普通项目service模块 {}", moduleDir.getPath());
    }
}