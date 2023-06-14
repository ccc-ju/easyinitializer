package com.deepinnet.initializr.domain.service.module.impl;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.BaseModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/14
 */

@Service
public class GenerationLogback extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationYml.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {

        File file = new File(
                projectsRoot + "/src/main/resources/",
                "logback-spring.xml"
        );

        // 写入文件
        super.writeFile(file, "logback-spring.ftl", projectInfo);

        logger.info("创建logback日志文件 logback-spring.xml {}", file.getPath());

    }

}
