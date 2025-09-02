package com.deepinnet.initializr.domain.service;

import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 普通项目结构生成器(六模块结构: api-model, dal, service, web, app-starter, common)
 * 
 * @author system
 * @since 2025/09/02
 */
@Service("simpleProjectGenerator")
public class SimpleProjectGeneratorImpl {

    private final Logger logger = LoggerFactory.getLogger(SimpleProjectGeneratorImpl.class);

    @Resource
    private GenerationSimpleParentPom generationSimpleParentPom;
    @Resource
    private GenerationSimpleApiModel generationSimpleApiModel;
    @Resource
    private GenerationSimpleDal generationSimpleDal;
    @Resource
    private GenerationSimpleService generationSimpleService;
    @Resource
    private GenerationSimpleWeb generationSimpleWeb;
    @Resource
    private GenerationSimpleAppStarter generationSimpleAppStarter;
    @Resource
    private GenerationSimpleCommon generationSimpleCommon;
    @Resource
    private GenerationIgnore generationIgnore;
    @Resource
    private GenerationReadme generationReadme;

    public void generator(ProjectInfo projectInfo) throws Exception {
        String property = System.getProperty("user.dir");
        String projectName = projectInfo.getName();
        String projectsRoot = property + "/" + projectName + "/";

        logger.info("开始生成普通项目结构: {}", projectName);

        // 父工程生成
        generationIgnore.doGeneration(projectInfo, projectsRoot);
        // generationReadme.doGeneration(projectInfo, projectsRoot);
        generationSimpleParentPom.doGeneration(projectInfo, projectsRoot);

        // 各模块生成
        generationSimpleApiModel.doGeneration(projectInfo, projectsRoot);
        generationSimpleDal.doGeneration(projectInfo, projectsRoot);
        generationSimpleService.doGeneration(projectInfo, projectsRoot);
        generationSimpleWeb.doGeneration(projectInfo, projectsRoot);
        generationSimpleAppStarter.doGeneration(projectInfo, projectsRoot);
        generationSimpleCommon.doGeneration(projectInfo, projectsRoot);

        logger.info("普通项目结构生成完成: {}", projectName);
    }
}