package com.deepinnet.initializr.domain.service;

import com.deepinnet.initializr.application.IProjectGenerator;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.domain.service.module.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 项目结构生成
 * @author chenjiaju
 * @since 2023/06/05
 */

@Service
public class ProjectGeneratorImpl implements IProjectGenerator {

    private final Logger logger = LoggerFactory.getLogger(ProjectGeneratorImpl.class);

    @Resource
    private GenerationApplication generationApplication;
    @Resource
    private GenerationYml generationYml;
    @Resource
    private GenerationParentPom generationParentPom;
    @Resource
    private GenerationTest generationTest;
    @Resource
    private GenerationIgnore generationIgnore;
    @Resource
    private GenerationPackageInfo generationPackageInfo;
    @Resource
    private GenerationReadme generationReadme;
    @Resource
    private GenerationAppStarterPom generationAppStarterPom;
    @Resource
    private GenerationBizPom generationBizPom;
    @Resource
    private GenerationBizServiceImplPom generationBizServiceImplPom;

    @Resource
    private GenerationBizServiceImplInfo generationBizServiceImplInfo;

    @Resource
    private GenerationCommonPom generationCommonPom;

    @Resource
    private GenerationCommonDalPom generationCommonDalPom;

    @Resource
    private GenerationCommonDalInfo generationCommonDalInfo;

    @Resource
    private GenerationCommonServicePom generationCommonServicePom;

    @Resource
    private GenerationCommonServiceFacadePom generationCommonServiceFacadePom;

    @Resource
    private GenerationCommonServiceFacadeInfo generationCommonServiceFacadeInfo;

    @Resource
    private GenerationCommonServiceIntegrationPom generationCommonServiceIntegrationPom;

    @Resource
    private GenerationCommonServiceIntegrationInfo generationCommonServiceIntegrationInfo;

    @Resource
    private GenerationCommonUtilPom generationCommonUtilPom;

    @Resource
    private GenerationCommonUtilInfo generationCommonUtilInfo;

    @Resource
    private GenerationCorePom generationCorePom;

    @Resource
    private GenerationCoreModelPom generationCoreModelPom;

    @Resource
    private GenerationCoreServicePom generationCoreServicePom;

    @Resource
    private GenerationCoreModelInfo generationCoreModelInfo;

    @Resource
    private GenerationCoreServiceInfo generationCoreServiceInfo;

    @Resource
    private GenerationApiPom generationApiPom;

    @Resource
    private GenerationApiPackageInfo generationApiPackageInfo;

    @Resource
    private GenerationKnife4jConfiguration generationKnife4jConfiguration;

    @Override
    public void generator(ProjectInfo projectInfo) throws Exception {

        // URL resource = this.getClass().getResource("/");
        String property = System.getProperty("user.dir");
        String projectName = projectInfo.getName();
        String projectsRoot = property + "/" + projectName + "/";
        // 父工程 生成 .gitignore
        generationIgnore.doGeneration(projectInfo, projectsRoot);
        // 父工程 生成 README.ftl
        generationReadme.doGeneration(projectInfo, projectsRoot);
        // 父工程 生成 pom.xml 文件
        generationParentPom.doGeneration(projectInfo, projectsRoot);

        // app-starter工程
        generatorAppStarter(projectInfo, projectName, projectsRoot);
        // biz工程
        generatorBiz(projectInfo, projectName, projectsRoot);
        // common 工程
        generatorCommon(projectInfo, projectName, projectsRoot);
        // core 工程
        generatorCore(projectInfo, projectName, projectsRoot);
        // api 工程
        generatorApi(projectInfo, projectName, projectsRoot);

    }

    private void generatorApi(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + "/" + projectName + "-" + "api" + "/";

        // 1. 生成 pom.xml
        generationApiPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成api工程描述文件
        generationApiPackageInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorAppStarter(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + "/" + projectName + "-" + "app-starter" + "/";
        String lastPackageName = projectInfo.getArtifactId().replaceAll("-", "").toLowerCase();
        //启动类名称
        StringBuffer applicationJavaName = new StringBuffer("ApplicationStarter");

        // 1. 生成 application.yml
        generationYml.doGeneration(projectInfo, projectsRoot);

        // 2. 生成 pom.xml
        generationAppStarterPom.doGeneration(projectInfo, projectsRoot);

        // 3. 创建  Application.java
        generationApplication.doGeneration(projectInfo, projectsRoot, lastPackageName, applicationJavaName);

        // 4.生成knife4j配置类
        generationKnife4jConfiguration.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorBiz(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + "/" + projectName + "-" + "biz" + "/";
        // 1. 生成 pom.xml
        generationBizPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成biz-service-impl工程
        generatorBizServiceImpl(projectInfo, projectName, projectsRoot);

    }

    private void generatorBizServiceImpl(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-biz-service-impl" + "/";
        // 1.生成pom.xml
        generationBizServiceImplPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成biz-service-impl描述文件
        generationBizServiceImplInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorCommon(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + "/" + projectName + "-" + "common" + "/";
        // 1.生成common pom.xml
        generationCommonPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-dal 工程
        generatorCommonDal(projectInfo, projectName, projectsRoot);

        // 3.生成common-service 工程
        generatorCommonService(projectInfo, projectName, projectsRoot);

        // 4.生成common-util 工程
        generatorCommonUtil(projectInfo, projectName, projectsRoot);
    }

    private void generatorCommonDal(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-common-dal" + "/";
        // 1.生成pom.xml
        generationCommonDalPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-dal描述文件
        generationCommonDalInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorCommonService(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-common-service" + "/";
        // 1.生成pom.xml
        generationCommonServicePom.doGeneration(projectInfo, projectsRoot);

        // 2.common-service-facade 工程
        generatorCommonServiceFacade(projectInfo, projectName, projectsRoot);

        // 3.common-service-integration 工程
        generatorCommonServiceIntegration(projectInfo, projectName, projectsRoot);
    }

    private void generatorCommonServiceFacade(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-common-service-facade" + "/";
        // 1.生成pom.xml
        generationCommonServiceFacadePom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-service-facade描述文件
        generationCommonServiceFacadeInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorCommonServiceIntegration(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-common-service-integration" + "/";
        // 1.生成pom.xml
        generationCommonServiceIntegrationPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-service-integration描述文件
        generationCommonServiceIntegrationInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorCommonUtil(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-common-util" + "/";

        // 1.生成pom文件
        generationCommonUtilPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-util描述文件
        generationCommonUtilInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorCore(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + "/" + projectName + "-" + "core" + "/";
        // 1.core pom.xml
        generationCorePom.doGeneration(projectInfo, projectsRoot);

        // 2.生成core-model 工程
        generatorCoreModel(projectInfo, projectName, projectsRoot);

        // 3.生成core-service 工程
        generatorCoreService(projectInfo, projectName, projectsRoot);
    }

    private void generatorCoreModel(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-core-model" + "/";
        // 1.生成pom.xml
        generationCoreModelPom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-dal描述文件
        generationCoreModelInfo.doGeneration(projectInfo, projectsRoot);
    }

    private void generatorCoreService(ProjectInfo projectInfo, String projectName, String projectsRoot) throws Exception {
        projectsRoot = projectsRoot + projectName + "-core-service" + "/";
        // 1.生成pom.xml
         generationCoreServicePom.doGeneration(projectInfo, projectsRoot);

        // 2.生成common-dal描述文件
        generationCoreServiceInfo.doGeneration(projectInfo, projectsRoot);
    }

}
