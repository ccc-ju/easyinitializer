<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.deepinnet</groupId>
        <artifactId>${artifactId}</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>${artifactId}-api</artifactId>

    <properties>
        <biz-service-impl.version>1.0-SNAPSHOT</biz-service-impl.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-biz-service-impl</artifactId>
            <version>${r"${biz-service-impl.version}"}</version>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>

</project>