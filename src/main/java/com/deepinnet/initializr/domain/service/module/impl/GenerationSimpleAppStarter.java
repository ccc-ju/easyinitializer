package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目app-starter模块生成器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class GenerationSimpleAppStarter extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationSimpleAppStarter.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        String moduleName = projectInfo.getName() + "-app-starter";
        File moduleDir = new File(projectsRoot, moduleName);
        if (!moduleDir.exists()) {
            moduleDir.mkdirs();
        }

        // 创建pom.xml
        File pomFile = new File(moduleDir, "pom.xml");
        super.writeFile(pomFile, "simple-app-starter-pom.ftl", projectInfo);

        // 创建目录结构
        String packagePath = projectInfo.getGroupId().replaceAll("\\.", "/") + "/";
        File srcMainJavaDir = new File(moduleDir, "src/main/java/" + packagePath);
        if (!srcMainJavaDir.exists()) {
            srcMainJavaDir.mkdirs();
        }

        // 创建启动类
        String applicationClassName = projectInfo.getName().replaceAll("-", "") + "Application";
        applicationClassName = applicationClassName.substring(0, 1).toUpperCase() + applicationClassName.substring(1);
        File applicationFile = new File(srcMainJavaDir, applicationClassName + ".java");
        super.writeFile(applicationFile, "simple-application.ftl", projectInfo);

        // 创建resources目录
        File resourcesDir = new File(moduleDir, "src/main/resources");
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs();
        }

        // 创建配置文件
        File applicationYmlFile = new File(resourcesDir, "application.yml");
        super.writeFile(applicationYmlFile, "yml.ftl", projectInfo);

        // 创建logback配置
        File logbackFile = new File(resourcesDir, "logback-spring.xml");
        super.writeFile(logbackFile, "logback-spring.ftl", projectInfo);

        logger.info("创建普通项目app-starter模块 {}", moduleDir.getPath());
    }
}