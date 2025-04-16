<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.deepinnet</groupId>
        <artifactId>${artifactId}-core</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>${artifactId}-core-model</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-common-service-facade</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-common-dal</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-common-service-integration</artifactId>
        </dependency>

        <#if enableDubbo>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        </#if>
    </dependencies>

</project>