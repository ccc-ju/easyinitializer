package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * app-starter生成
 */
@Service
public class GenerationApplication extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationApplication.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {

        ApplicationInfo applicationInfo = new ApplicationInfo(
                projectInfo.getGroupId(),
                applicationJavaName.toString(),
                projectInfo.getArtifactId()
        );

        String packagePath = applicationInfo.getPackageName().replace(".", "/") + "/";

        File file = new File(projectsRoot + "/src/main/java/" + packagePath,
                applicationInfo.getClassName() + ".java");

        // 写入文件
        super.writeFile(file, "application.ftl", applicationInfo);

        logger.info("创建主入口类 Application.java {}", file.getPath());

        File knife4JConfigurationFile = new File(projectsRoot + "/src/main/java/" + packagePath + "/config",
                "Knife4jConfiguration.java");

        // 写入文件
        super.writeFile(knife4JConfigurationFile, "knife4j-configuration.ftl", applicationInfo);

        logger.info("创建Knife4jConfiguration.java {}", knife4JConfigurationFile.getPath());
    }

}
