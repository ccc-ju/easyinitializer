package com.deepinnet.initializr.domain.model;

import lombok.Data;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */

@Data
public class ApplicationInfo {

    private String packageName;
    private String className;
    private String artifactId;
    private Boolean enableNacos;
    private Boolean enableDubbo;

    public ApplicationInfo() {
    }

    public ApplicationInfo(String packageName) {
        this.packageName = packageName;
    }

    public ApplicationInfo(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public ApplicationInfo(String packageName, String className, String artifactId, Boolean enableDubbo, Boolean enableNacos) {
        this.packageName = packageName;
        this.className = className;
        this.artifactId = artifactId;
        this.enableNacos = enableNacos;
        this.enableDubbo = enableDubbo;
    }
}
