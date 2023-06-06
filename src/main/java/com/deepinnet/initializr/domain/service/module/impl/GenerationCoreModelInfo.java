package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 生成core-model描述文件
 * 生成描述文件 package-info.java
 */
@Service
public class GenerationCoreModelInfo extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCoreModelInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        String packageName = projectInfo.getGroupId();

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "core/model/",
                "XModel.java"), "package-info.ftl", new ApplicationInfo(packageName + ".core.model"));


        logger.info("创建core-model分层和描述文件 {}", "package-info.java");
    }

}
