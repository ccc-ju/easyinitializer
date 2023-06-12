package com.deepinnet.initializr.domain.service.module.impl;

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
public class GenerationIgnore extends BaseModule {

    private final Logger logger = LoggerFactory.getLogger(GenerationParentPom.class);

    public void doGeneration(ProjectInfo projectInfo, String projectsRoot) throws Exception {
        File file = new File(
                projectsRoot,
                ".gitignore"
        );

        // 写入文件
        super.writeFile(file, "ignore.ftl", projectInfo);

        logger.info("创建配置文件 .gitignore {}", file.getPath());
    }

}
