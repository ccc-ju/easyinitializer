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
public class GenerationCoreServiceInfo extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCoreServiceInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        String packageName = projectInfo.getGroupId();

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "core/config/",
                "XConfig.java"), "package-info.ftl", new ApplicationInfo(packageName + ".core.config"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "core/repository/",
                "XRepository.java"), "package-info.ftl", new ApplicationInfo(packageName + ".core.repository"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "core/repository/impl/",
                "XRepositoryImpl.java"), "package-info.ftl", new ApplicationInfo(packageName + ".core.repository.impl"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "core/service/",
                "XDomainService.java"), "package-info.ftl", new ApplicationInfo(packageName + ".core.service"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "core/service/impl/",
                "XDomainServiceImpl.java"), "package-info.ftl", new ApplicationInfo(packageName + ".core.service.impl"));


        logger.info("创建core-model分层和描述文件 {}", "package-info.java");
    }

}
