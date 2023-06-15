package com.deepinnet.initializr.dto;

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

}
