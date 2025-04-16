package com.deepinnet.initializr.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = -8967702842381232912L;

    private String groupId;

    private String artifactId;

    private String version;

    private String name;

    private String description;

    private String dbType;

    private String databaseLink;

    private String username;

    private String password;

    /**
     * 是否启用nacos
     */
    private Boolean enableNacos;

    /**
     * 是否启用Dubbo服务框架
     */
    private Boolean enableDubbo;

    public ProjectInfo(String groupId, String artifactId, String version, String name, String description, Boolean enableNacos, Boolean enableDubbo) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.name = name;
        this.description = description;
        this.enableNacos = enableNacos;
        this.enableDubbo = enableDubbo;
    }
}
