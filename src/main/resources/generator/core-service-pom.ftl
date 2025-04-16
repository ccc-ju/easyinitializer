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

    <artifactId>${artifactId}-core-service</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-core-model</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-common-util</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-core-model</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-common-dal</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>${artifactId}-common-service-facade</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.github.jsonzou</groupId>
            <artifactId>jmockdata</artifactId>
            <version>4.3.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

</project>