package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 生成common-service-facade描述文件
 * 生成描述文件 package-info.java
 */
@Service
public class GenerationCommonServiceFacadeInfo extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationCommonServiceFacadeInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        String packageName = projectInfo.getGroupId();

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "common/dto/",
                "XDTO.java"), "package-info.ftl", new ApplicationInfo(packageName + ".common.dto"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "common/service/facade/",
                "XFacade.java"), "package-info.ftl", new ApplicationInfo(packageName + ".common.service.facade"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "common/vo/",
                "XVO.java"), "package-info.ftl", new ApplicationInfo(packageName + ".common.vo"));


        logger.info("创建common-service-facade分层和描述文件 {}", "package-info.java");
    }

}
