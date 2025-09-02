<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>
    <packaging>pom</packaging>
    <description>${description}</description>

    <modules>
        <module>${artifactId}-api-model</module>
        <module>${artifactId}-service</module>
        <module>${artifactId}-web</module>
        <module>${artifactId}-dal</module>
        <module>${artifactId}-app-starter</module>
        <module>${artifactId}-common</module>
    </modules>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>11</java.version>

        <spring-boot.version>2.6.13</spring-boot.version>
        <jasypt.version>3.0.5</jasypt.version>
        <pageHelper.version>1.4.7</pageHelper.version>
        <mapstruct.version>1.5.0.Final</mapstruct.version>
        <#if dbType?? && dbType != "">
        <mybatis-plus.version>3.5.3</mybatis-plus.version>
        <beetl.version>3.0.9.RELEASE</beetl.version>
        </#if>
        <#if dbType?? && dbType == "postgresql">
        <dynamic-datasource.version>3.5.1</dynamic-datasource.version>
        </#if>
        <lombok.version>1.18.24</lombok.version>
        <feigin.version>3.1.9</feigin.version>
        <satoken.version>1.38.0</satoken.version>
        <spring-redis-starter.version>2.6.13</spring-redis-starter.version>
        <jts.version>1.19.0</jts.version>
        <easyexcel.version>3.2.1</easyexcel.version>
        <postgis.version>2.5.0</postgis.version>
        <logback.version>6.6</logback.version>
        <commons-pool2.version>2.12.1</commons-pool2.version>
        <hutool.version>5.8.9</hutool.version>
        <commons-lang3.version>3.12.0</commons-lang3.version>
        <commons-collections4.version>4.4</commons-collections4.version>
        <lombok-mapstruct-binding.version>0.2.0</lombok-mapstruct-binding.version>

        <springfox.version>3.0.0</springfox.version>
        <knife4j.version>4.4.0</knife4j.version>

        <api-model.version>${r"${version}"}</api-model.version>

        <#if dbType?? && dbType == "mysql">
        <mysql.version>8.0.28</mysql.version>
        <druid.version>1.2.17</druid.version>
        </#if>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok-mapstruct-binding</artifactId>
            <version>${r"${lombok-mapstruct-binding.version}"}</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${r"${mapstruct.version}"}</version>
        </dependency>

        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${r"${mapstruct.version}"}</version>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${r"${hutool.version}"}</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>${r"${commons-lang3.version}"}</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>${r"${commons-collections4.version}"}</version>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-openfeign</artifactId>
                <version>${r"${feigin.version}"}</version>
            </dependency>

            <dependency>
                <groupId>cn.dev33</groupId>
                <artifactId>sa-token-spring-boot-starter</artifactId>
                <version>${r"${satoken.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-redis</artifactId>
                <version>${r"${spring-redis-starter.version}"}</version>
            </dependency>

            <dependency>
                <groupId>cn.dev33</groupId>
                <artifactId>sa-token-redis-jackson</artifactId>
                <version>${r"${satoken.version}"}</version>
            </dependency>

            <dependency>
                <groupId>cn.dev33</groupId>
                <artifactId>sa-token-jwt</artifactId>
                <version>${r"${satoken.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-pool2</artifactId>
                <version>${r"${commons-pool2.version}"}</version>
            </dependency>

            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-core</artifactId>
                <version>${r"${springfox.version}"}</version>
            </dependency>

            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-swagger2</artifactId>
                <version>${r"${springfox.version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-openapi2-spring-boot-starter</artifactId>
                <version>${r"${knife4j.version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>easyexcel</artifactId>
                <version>${r"${easyexcel.version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.github.ulisesbocchio</groupId>
                <artifactId>jasypt-spring-boot-starter</artifactId>
                <version>${r"${jasypt.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${r"${spring-boot.version}"}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-app-starter</artifactId>
                <version>${r"${project.version}"}</version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-dal</artifactId>
                <version>${r"${project.version}"}</version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-service</artifactId>
                <version>${r"${project.version}"}</version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-web</artifactId>
                <version>${r"${project.version}"}</version>
            </dependency>

            <dependency>
                <groupId>net.logstash.logback</groupId>
                <artifactId>logstash-logback-encoder</artifactId>
                <version>${r"${logback.version}"}</version>
            </dependency>

            <dependency>
                <groupId>${groupId}</groupId>
                <artifactId>${artifactId}-api-model</artifactId>
                <version>${r"${api-model.version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>${r"${pageHelper.version}"}</version>
            </dependency>

            <#if dbType?? && dbType != "">
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${r"${mybatis-plus.version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-generator</artifactId>
                <version>${r"${mybatis-plus.version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.ibeetl</groupId>
                <artifactId>beetl</artifactId>
                <version>${r"${beetl.version}"}</version>
            </dependency>
            </#if>

            <#if dbType?? && dbType == "postgresql">
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
                <version>${r"${dynamic-datasource.version}"}</version>
            </dependency>

            <dependency>
                <groupId>net.postgis</groupId>
                <artifactId>postgis-jdbc</artifactId>
                <version>${r"${postgis.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.locationtech.jts</groupId>
                <artifactId>jts-core</artifactId>
                <version>${r"${jts.version}"}</version>
            </dependency>
            </#if>

            <#if dbType?? && dbType == "mysql">
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>${r"${mysql.version}"}</version>
                </dependency>

                <dependency>
                    <groupId>com.alibaba</groupId>
                    <artifactId>druid-spring-boot-starter</artifactId>
                    <version>${r"${druid.version}"}</version>
                </dependency>
            </#if>

            <dependency>
                <groupId>io.swagger</groupId>
                <artifactId>swagger-annotations</artifactId>
                <version>1.5.19</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <finalName>${artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>${r"${java.version}"}</source>
                    <target>${r"${java.version}"}</target>
                    <encoding>${r"${project.build.sourceEncoding}"}</encoding>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${r"${lombok.version}"}</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${r"${mapstruct.version}"}</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok-mapstruct-binding</artifactId>
                            <version>0.2.0</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>