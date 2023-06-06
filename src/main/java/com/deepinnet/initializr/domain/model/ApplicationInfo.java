package com.deepinnet.initializr.domain.model;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
public class ApplicationInfo {

    private String packageName;
    private String className;
    private String artifactId;

    public ApplicationInfo() {
    }

    public ApplicationInfo(String packageName) {
        this.packageName = packageName;
    }

    public ApplicationInfo(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public ApplicationInfo(String packageName, String className, String artifactId) {
        this.packageName = packageName;
        this.className = className;
        this.artifactId = artifactId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
