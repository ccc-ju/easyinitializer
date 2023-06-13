package com.deepinnet.initializr.domain.model;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
public class ProjectInfo {

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatabaseLink() {
        return databaseLink;
    }

    public void setDatabaseLink(String databaseLink) {
        this.databaseLink = databaseLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
