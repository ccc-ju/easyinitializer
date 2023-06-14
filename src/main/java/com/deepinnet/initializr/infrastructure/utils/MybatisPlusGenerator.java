package com.deepinnet.initializr.infrastructure.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.exception.InitializerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  mybatisplus 代码生成器
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/13
 */
public class MybatisPlusGenerator {

    // 项目名
    static String projectName;

    // 数据源配置
    // url
    static String url;
    // 用户名
    static String username;
    // 密码
    static String password;

    // 全局配置
    // 输出目录
    static String outputDir;
    // 作者
    static String author = "chenjiaju";
    // 时间策略
    static DateType dateType = DateType.TIME_PACK;
    // 注释日期
    static String commentDate = "yyyy-MM-dd HH:mm:ss";

    // 模板配置
    // 禁用模板 本项目配置不生成 controller
    static TemplateType[] disableTemplateType = {
            // TemplateType.ENTITY,
            // TemplateType.MAPPER,
            // TemplateType.XML,
            // TemplateType.SERVICE,
            // TemplateType.SERVICEIMPL,
            TemplateType.CONTROLLER
    };

    // 策略配置
    // 需要生成的表名 此处配置需要生成的表名
    static String[] tableNames;
    // 过滤表名前缀
    static String tablePrefix = "";
    // 过滤表名后缀
    static String tableSuffix = "";
    // 过滤表字段后缀
    static String fieldPrefix = "";
    // 过滤表字段后缀
    static String fieldSuffix = "";

    // Controller 策略
    // 设置 Controller 父类
    static String controllerSuperClass = "";
    // 格式化 Controller 接口名称。%s:表名。默认是"%sController"
    static String formatControllerName = "%sController";

    // Service 策略, Repository
    // 设置 Service 父类
    static String superServiceClass = "";
    // 设置 ServiceImpl 父类
    static String superServiceImplClass = "";
    // 格式化 Service 名称
    static String formatServiceFileName = "%sRepository";
    // 格式化 ServiceImpl 名称
    static String formatServiceImplFileName = "%sRepositoryImpl";

    // mapper接口以及xml文件策略
    // 实体类父类的全名
    static String mapperSuperClass = "";
    // 格式化mapper接口名称。%s:表名。默认是"%sMapper"
    static String formatMapperFileName = "%sDao";
    // 格式化xml文件名称。%s:表名。默认是"%sMapper"
    static String formatXmlFileName = "%sMapper";

    // entity 策略
    // 实体类父类的全名
    static String entitySuperClass = "";
    // 乐观锁字段名(数据库)
    static String versionColumnName = "";
    // 乐观锁属性名(实体)
    static String versionPropertyName = "";
    // 逻辑删除字段名(数据库)
    static String logicDeleteColumnName = "";
    // 逻辑删除属性名(实体)
    static String logicDeletePropertyName = "isDeleted";
    // 添加父类公共字段
    static String[] superEntityColumns = {};
    // 添加忽略字段
    static String[] ignoreColumns = {};

    // 自动填充的字段和时机
    static List<IFill> fillColumns = new ArrayList<IFill>(){};

    // 格式化实体类名称。%s:表名。默认是"%s"
    static String formatFileName = "%sDO";

    static String parentPackageName;

    // 包配置
    // 实体类包名
    static String entityPackageName = "common.dal.dataobject";
    // mapper接口包名
    static String mapperPackageName = "common.dal.dao";
    // service接口包名
    static String servicePackageName = "core.repository";
    // service实现类包名
    static String serviceImplPackageName = "core.repository.impl";
    // controller包名
    static String controllerPackageName = "controller";

    public static String[] getTableNames(ProjectInitDTO projectInitDTO) {
        String dataBaseName = projectInitDTO.getDatabaseLink().substring(projectInitDTO.getDatabaseLink().lastIndexOf("/") + 1);
        ResultSet resultSet = null;
        Connection connection = null;
        List<String> tableList = new ArrayList<>();
        try {
            String url = "jdbc:mysql://" + projectInitDTO.getDatabaseLink() + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(url, projectInitDTO.getUsername(), projectInitDTO.getPassword());
            // 拿数据库元数据
            DatabaseMetaData metaData = connection.getMetaData();

            // 获取所有表名
            resultSet = metaData.getTables(dataBaseName, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tableList.add(tableName);
            }
            return ArrayUtil.toArray(tableList, String.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InitializerException("0005", "数据库连接异常");
        } finally {
            try {
                if (ObjectUtil.isNotNull(resultSet)) {
                    resultSet.close();
                }
                if (ObjectUtil.isNotNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void generator(ProjectInitDTO projectInitDTO, String path) throws ClassNotFoundException {
        // 不手动加载数据库驱动会导致'No suitable driver found for jdbc:mysql://xxx:3306/xxx'异常
        Class.forName("com.mysql.cj.jdbc.Driver");
        tableNames = getTableNames(projectInitDTO);
        projectName = projectInitDTO.getProjectName();
        url = "jdbc:mysql://" + projectInitDTO.getDatabaseLink() + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        username = projectInitDTO.getUsername();
        password = projectInitDTO.getPassword();
        outputDir = path;
        parentPackageName = projectInitDTO.getGroupId();

        // 生成器
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> builder
                        // 禁止打开输出目录
                        .disableOpenDir()
                        // 指定输出目录
                        .outputDir(outputDir)
                        // 设置作者
                        .author(author)
                        // 时间策略
                        .dateType(dateType)
                        // 注释日期
                        .commentDate(commentDate)
                        // 开启 swagger 模式
                        //.enableSwagger()
                        .build())
                // 包配置
                .packageConfig(builder -> builder
                        // 父类包名
                        .parent(parentPackageName)
                        // Controller 包名
                        .controller(controllerPackageName)
                        // Service 包名
                        .service(servicePackageName)
                        // ServiceImpl 包名
                        .serviceImpl(serviceImplPackageName)
                        // Mapper 包名
                        .mapper(mapperPackageName)
                        // Entity 包名
                        .entity(entityPackageName)
                        // 设置mapperXml生成路径
                        .pathInfo(pathInfo(projectName, projectInitDTO.getGroupId()))
                        .build())
                // 模板配置
                .templateConfig(builder -> builder
                        .disable(disableTemplateType)
                        .build()
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        // 设置需要生成的表名
                        .addInclude(tableNames)
                        // 设置过滤表名前缀
                        .addTablePrefix(tablePrefix)
                        // 设置过滤表名后缀
                        .addTableSuffix(tableSuffix)
                        // 设置过滤表字段前缀
                        .addFieldPrefix(fieldPrefix)
                        // 设置过滤表字段后缀
                        .addFieldSuffix(fieldSuffix)

                        // Controller 策略
                        .controllerBuilder()
                        // 覆盖已生成文件
                        .fileOverride()
                        // Controller父类
                        .superClass(controllerSuperClass)
                        // 开启生成 @RestController 控制器
                        .enableRestStyle()
                        // 格式化 Controller 接口名称。%s:表名。默认是"%sController"
                        .formatFileName(formatControllerName)

                        // Service 策略
                        .serviceBuilder()
                        // 覆盖已生成文件
                        .fileOverride()
                        // 设置 service 接口父类
                        .superServiceClass(StrUtil.isEmpty(superServiceClass) ? ConstVal.SUPER_SERVICE_CLASS : superServiceClass)
                        // 设置 service 实现类父类
                        .superServiceImplClass(StrUtil.isEmpty(superServiceImplClass) ? ConstVal.SUPER_SERVICE_IMPL_CLASS : superServiceImplClass)
                        // 格式化 service 接口名称。%s:表名。默认是"I%sService"
                        .formatServiceFileName(formatServiceFileName)
                        // 格式化 service 实现类名称。%s:表名。默认是"%sServiceImpl"
                        .formatServiceImplFileName(formatServiceImplFileName)

                        // mapper策略
                        .mapperBuilder()
                        // 覆盖已生成文件
                        .fileOverride()
                        // mapper接口的父类
                        .superClass(StrUtil.isEmpty(mapperSuperClass) ? ConstVal.SUPER_MAPPER_CLASS : mapperSuperClass)
                        // 开启 @Mapper 注解
                        //.enableMapperAnnotation()
                        // 启用 BaseResultMap 生成
                        .enableBaseResultMap()
                        // 启用 BaseColumnList
                        .enableBaseColumnList()
                        // 格式化mapper接口名称。%s:表名。默认是"%sDao"
                        .formatMapperFileName(formatMapperFileName)
                        // 格式化xml文件名称。%s:表名。默认是"%sMapper"
                        .formatXmlFileName(formatXmlFileName)

                        // entity 策略
                        .entityBuilder()
                        // 覆盖已生成文件
                        .fileOverride()
                        // 实体类的父类
                        .superClass(entitySuperClass)
                        // 开启 Lombok 注解
                        .enableLombok()
                        // 开启生成实体时生成字段注解
                        .enableTableFieldAnnotation()
                        // 开启 ActiveRecord 模型
                        .enableActiveRecord()
                        // 乐观锁字段名(数据库)
                        .versionColumnName(versionColumnName)
                        // 乐观锁属性名(实体)
                        .versionPropertyName(versionPropertyName)
                        // 逻辑删除字段名(数据库)
                        .logicDeleteColumnName(logicDeleteColumnName)
                        // 逻辑删除属性名(实体)
                        .logicDeletePropertyName(logicDeletePropertyName)
                        // 添加父类公共字段
                        .addSuperEntityColumns(superEntityColumns)
                        // 添加忽略字段
                        .addIgnoreColumns(ignoreColumns)
                        // 自动填充字段
                        .addTableFills(fillColumns)
                        // 格式化实体类名称。%s:表名。默认是"%s"
                        .formatFileName(formatFileName)
                        .build())
//                .injectionConfig(config -> {
//                    // 自定义生成模板参数
//                    Map<String,Object> paramMap = new HashMap<>();
//                    // 自定义 生成类
//                    Map<String,String> customFileMap = new HashMap<>();
//                    // 领域层实体
//                    customFileMap.put(File.separator + "%s.java", "/templates/myEntity.java.ftl");
//                    config.customMap(paramMap);
//                    config.customFile(customFileMap);
//                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 各文件的输出路径
     * @return pathInfo
     */
    public static Map<OutputFile, String> pathInfo(String projectName, String groupId) {
        // 基础路径
        String basePath = System.getProperty("user.dir") + "/" + projectName;
        // java代码的路径
        String javaPath = "/src/main/java";
        // 资源文件的路径
        String resourcePath = "/src/main/resources";

        // 存放各文件的项目名（单模块项目不用配置）
        // xml文件所在模块
        String xmlProject = "/"+ projectName +"-common/" + projectName + "-common-dal";
        // 数据库DO文件所在模块
        String entityProject = "/" + projectName + "-common/" + projectName + "-common-dal";
        // mapper接口文件所在模块
        String mapperProject = "/" + projectName + "-common/" + projectName + "-common-dal";
        // repository接口文件所在模块（对应service）
        String serviceProject = "/" + projectName + "-core/" + projectName + "-core-service";
        // repositoryImpl接口实现文件所在模块（对应serviceImpl）
        String serviceImplProject = "/" + projectName + "-core/" + projectName + "-core-service";
        // 不生成controller
        // String controllerProject =  "";

        String position = groupId.replace(".", "/");
        /*存放各文件的包路径*/
        String xmlPackage =         "/mybatis/mapper";
        String entityPackage = position + "/common/dal/dataobject";
        String mapperPackage = position + "/common/dal/dao";
        String servicePackage = position + "/core/repository";
        String serviceImplPackage = position + "/core/repository/impl";
        // 不生成controller
        // String controllerPackage =  "/com.generator/controller";

        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.xml, basePath + xmlProject + resourcePath + xmlPackage);
        pathInfo.put(OutputFile.entity, basePath + entityProject + javaPath + entityPackage);
        pathInfo.put(OutputFile.mapper, basePath + mapperProject + javaPath + mapperPackage);
        pathInfo.put(OutputFile.service, basePath + serviceProject + javaPath + servicePackage);
        pathInfo.put(OutputFile.serviceImpl, basePath + serviceImplProject + javaPath + serviceImplPackage);
        // 不生成controller
        // pathInfo.put(OutputFile.controller, basePath + controllerProject + javaPath + controllerPackage);
        return pathInfo;
    }


}
