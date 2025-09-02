package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目web模块生成器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class GenerationSimpleWeb extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationSimpleWeb.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        String moduleName = projectInfo.getName() + "-web";
        File moduleDir = new File(projectsRoot, moduleName);
        if (!moduleDir.exists()) {
            moduleDir.mkdirs();
        }

        // 创建pom.xml
        File pomFile = new File(moduleDir, "pom.xml");
        super.writeFile(pomFile, "simple-web-pom.ftl", projectInfo);

        // 创建目录结构
        String packagePath = projectInfo.getGroupId().replaceAll("\\.", "/") + "/";
        File srcMainJavaDir = new File(moduleDir, "src/main/java/" + packagePath + "/web");
        if (!srcMainJavaDir.exists()) {
            srcMainJavaDir.mkdirs();
        }

        // 创建controller目录
        File controllerDir = new File(srcMainJavaDir, "controller");
        if (!controllerDir.exists()) {
            controllerDir.mkdirs();
        }

        logger.info("创建普通项目web模块 {}", moduleDir.getPath());
    }
}