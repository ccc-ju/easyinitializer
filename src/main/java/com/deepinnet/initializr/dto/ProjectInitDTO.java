package com.deepinnet.initializr.dto;

import com.deepinnet.initializr.domain.enums.DbTypeEnum;
import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *  项目生成入参描述
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */

@Getter
@Setter
public class ProjectInitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目坐标地址 (com.deepinnet.xxx)
     */
    private String groupId;

    /**
     * 项目项目名称
     */
    private String projectName;

    /**
     * 项目版本 1.0.0-SNAPSHOT
     */
    private String version;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 项目类型（新增）
     */
    private InitializerTypeEnum projectType = InitializerTypeEnum.DEEPINNET;

    /**
     * 数据库类型
     */
    private DbTypeEnum dbType;

    /**
     * 数据库连接
     */
    private String databaseLink;

    /**
     * 数据库连接用户名
     */
    private String username;

    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * 路径(前端无需此参数)
     */
    private String path;

    /**
     * 是否启用Nacos配置中心
     */
    private Boolean enableNacos = Boolean.TRUE;

    /**
     * 是否启用Dubbo服务框架
     */
    private Boolean enableDubbo = Boolean.TRUE;

}
