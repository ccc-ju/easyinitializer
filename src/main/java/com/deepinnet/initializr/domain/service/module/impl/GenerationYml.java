package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * yml多环境配置文件生成
 */
@Service
public class GenerationYml extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationYml.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        File file = new File(
                projectsRoot + "/src/main/resources/",
                "application.yml"
        );

        // 写入文件
        super.writeFile(file, "yml.ftl", null);

        logger.info("创建配置文件 application.yml {}", file.getPath());

        File bootstrap = new File(
                projectsRoot + "/src/main/resources/",
                "bootstrap.yml"
        );

        // 写入文件
        super.writeFile(bootstrap, "bootstrap-yml.ftl", null);

        logger.info("创建配置文件 bootstrap.yml {}", bootstrap.getPath());

        File applicationDev = new File(
                projectsRoot + "/src/main/resources/",
                "application-dev.yml"
        );

        // 写入文件
        super.writeFile(applicationDev, "dev-yml.ftl", null);

        logger.info("创建配置文件 application-dev.yml {}", applicationDev.getPath());

        File applicationLocal = new File(
                projectsRoot + "/src/main/resources/",
                "application-local.yml"
        );

        // 写入文件
        super.writeFile(applicationLocal, "local-yml.ftl", null);

        logger.info("创建配置文件 application-local.yml {}", applicationLocal.getPath());


        File applicationTest = new File(
                projectsRoot + "/src/main/resources/",
                "application-test.yml"
        );

        // 写入文件
        super.writeFile(applicationTest, "test-yml.ftl", null);

        logger.info("创建配置文件 application-test.yml {}", applicationTest.getPath());


        File applicationPre = new File(
                projectsRoot + "/src/main/resources/",
                "application-pre.yml"
        );

        // 写入文件
        super.writeFile(applicationPre, "pre-yml.ftl", null);

        logger.info("创建配置文件 application-pre.yml {}", applicationPre.getPath());


        File applicationProd = new File(
                projectsRoot + "/src/main/resources/",
                "application-prod.yml"
        );

        // 写入文件
        super.writeFile(applicationProd, "prod-yml.ftl", null);

        logger.info("创建配置文件 application-prod.yml {}", applicationProd.getPath());

    }

}
