package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 * <p>
 * 生成描述文件 package-info.java
 */
@Service
public class GenerationPackageInfo extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationPackageInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {

        String packageName = projectInfo.getGroupId();

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "application/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".application"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "domain/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".domain"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "infrastructure/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".infrastructure"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "interfaces/",
                "package-info.java"), "package-info.ftl", new ApplicationInfo(packageName + ".interfaces"));


        logger.info("创建DDD分层和描述文件 {}", "package-info.java");
    }

}
