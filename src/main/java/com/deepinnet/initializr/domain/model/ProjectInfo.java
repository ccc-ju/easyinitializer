package com.deepinnet.initializr.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */

@Data
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = -8967702842381232912L;

    private String groupId;

    private String artifactId;

    private String version;

    private String name;

    private String description;

    private String databaseLink;

    private String username;

    private String password;

    public ProjectInfo() {
    }

    public ProjectInfo(String groupId, String artifactId, String version, String name, String description) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.name = name;
        this.description = description;
    }

    public ProjectInfo(String groupId, String artifactId, String version, String name, String description, String databaseLink, String username, String password) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.name = name;
        this.description = description;
        this.databaseLink = databaseLink;
        this.username = username;
        this.password = password;
    }
}
