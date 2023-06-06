package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * biz层生成
 *
 * @author chenjiaju
 * @since 2023/06/05
 */

@Service
public class GenerationBizPackageInfo extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationBizPackageInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {

        String packageName = projectInfo.getGroupId() + "." + lastPackageName;

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + projectInfo.getArtifactId() + "/src/main/java/" + packagePath + "application/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".application"));

        super.writeFile(new File(projectsRoot + projectInfo.getArtifactId() + "/src/main/java/" + packagePath + "domain/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".domain"));

        super.writeFile(new File(projectsRoot + projectInfo.getArtifactId() + "/src/main/java/" + packagePath + "infrastructure/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".infrastructure"));

        super.writeFile(new File(projectsRoot + projectInfo.getArtifactId() + "/src/main/java/" + packagePath + "interfaces/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".interfaces"));


        logger.info("创建DDD分层和描述文件 {}", "package-info.java");
    }

}
