package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 生成biz-service-impl描述文件
 * 生成描述文件 package-info.java
 */
@Service
public class GenerationBizServiceImplInfo extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationBizServiceImplInfo.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        String packageName = projectInfo.getGroupId();

        String packagePath = packageName.replace(".", "/") + "/";

        // 写入文件
        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "biz/advice/",
                "FacadeExceptionHandler.java"), "package-info.ftl", new ApplicationInfo(packageName + ".biz.advice"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "biz/constants/",
                "XConstants.java"), "package-info.ftl", new ApplicationInfo(packageName + ".biz.constants"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "biz/converter/",
                "XConverter.java"), "package-info.ftl", new ApplicationInfo(packageName + ".biz.converter"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "biz/service/impl/",
                "XFacadeImpl.java"), "package-info.ftl", new ApplicationInfo(packageName + ".biz.service.impl"));

        super.writeFile(new File(projectsRoot + "/src/main/java/" + packagePath + "biz/util/",
                "XUtil.java"), "package-info.ftl", new ApplicationInfo(packageName + ".biz.util"));


        logger.info("创建biz分层和描述文件 {}", "package-info.java");
    }

}
