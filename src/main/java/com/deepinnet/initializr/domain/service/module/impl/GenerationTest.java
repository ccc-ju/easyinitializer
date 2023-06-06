package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ApplicationInfo;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
@Service
public class GenerationTest extends BaseModule {

    private Logger logger = LoggerFactory.getLogger(GenerationTest.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot, String lastPackageName, StringBuffer applicationJavaName) throws Exception {
        ApplicationInfo applicationInfo = new ApplicationInfo(
                projectInfo.getGroupId(),
                applicationJavaName.toString()
        );

        String packagePath = applicationInfo.getPackageName().replace(".", "/") + "/";

        File file = new File(projectsRoot + "/src/test/java/" + packagePath,
                "ApiTest.java");

        // 写入文件
        super.writeFile(file, "test.ftl", applicationInfo);

        logger.info("创建测试类 ApiTest.java {}", file.getPath());
    }

}
