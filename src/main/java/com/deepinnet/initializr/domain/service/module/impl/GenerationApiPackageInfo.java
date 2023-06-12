package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * api层生成
 *
 * @author chenjiaju
 * @since 2023/06/05
 */

@Service
public class GenerationApiPackageInfo extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationApiPackageInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        String packageName = projectInfo.getGroupId();

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "controller/",
                "DemoController.java"), "controller.ftl", new ApplicationInfo(packageName + ".controller", null, projectInfo.getArtifactId()));


        logger.info("创建controller分层和描述文件 {}", "controller.java");
    }

}
