<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.deepinnet</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <modules>
        <module>${artifactId}-api</module>
        <module>${artifactId}-app-starter</module>
        <module>${artifactId}-biz</module>
        <module>${artifactId}-common</module>
        <module>${artifactId}-core</module>
    </modules>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

        <!--spring相关依赖-->
        <spring.boot.version>2.6.11</spring.boot.version>
        <spring.cloud.version>2021.0.4</spring.cloud.version>
        <spring.cloud.alibaba.version>2021.0.4.0</spring.cloud.alibaba.version>
        <spring-data-redis.version>2.6.0</spring-data-redis.version>

        <!--中间件相关依赖-->
        <mysql.version>8.0.28</mysql.version>
        <druid.version>1.2.17</druid.version>
        <mybatis-plus.version>3.5.2</mybatis-plus.version>
        <rocketmq.version>2.2.2</rocketmq.version>
        <jedis.verision>3.6.3</jedis.verision>
        <dubbo.version>3.1.4</dubbo.version>
        <schedulerx2.version>1.7.9</schedulerx2.version>
        <schedulerx-plugin-kubernetes>1.0.1</schedulerx-plugin-kubernetes>

        <!--第三方库-->
        <lombok.version>1.18.24</lombok.version>
        <jackson.version>2.13.4</jackson.version>
        <common-lang3.version>3.12.0</common-lang3.version>
        <commons-collections4.version>4.4</commons-collections4.version>
        <hutool-all.version>5.8.9</hutool-all.version>
        <hutool.version>5.8.9</hutool.version>
        <knife.version>3.0.3</knife.version>
        <bcprov-jdk15on.version>1.70</bcprov-jdk15on.version>
        <jasypt.version>3.0.5</jasypt.version>

        <curator.version>2.6.0</curator.version>
        <perf4j.version>0.9.16</perf4j.version>
        <commons-io.version>2.4</commons-io.version>
        <aliyun-oss.version>3.15.1</aliyun-oss.version>

        <!-- 深度智联内部二方包 -->
        ${r"<!--<deepinnet-common-boot-starter-version>1.0.0.20230327-RELEASE</deepinnet-common-boot-starter-version>
        <deepinnet-common-lang-version>1.0.0.20230321-SNAPSHOT</deepinnet-common-lang-version>-->"}

        <!--本项目各模块的版本-->
        <${artifactId}.version>1.0-SNAPSHOT</${artifactId}.version>
        <${artifactId}-common-service-facade-version>1.0.0-SNAPSHOT</${artifactId}-common-service-facade-version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!-- 引入deepinnet-common-boot-starter -->
        ${r"<!-- <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>deepinnet-common-boot-starter</artifactId>
                <version>${deepinnet-common-boot-starter-version}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>deepinnet-common-lang</artifactId>
                <version>${deepinnet-common-lang-version}</version>
            </dependency>-->"}

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${r"${spring.boot.version}"}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>

            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${r"${spring.cloud.version}"}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>

            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${r"${spring.cloud.alibaba.version}"}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--中间件相关依赖-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-bom</artifactId>
                <version>${r"${dubbo.version}"}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-common-service-integration</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-common-util</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-biz-service-impl</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-api</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-core-service</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-common-dal</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-core-model</artifactId>
                <version>${r"${"}${artifactId}${r".version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.deepinnet</groupId>
                <artifactId>${artifactId}-common-service-facade</artifactId>
                <version>${r"${"}${artifactId}${r"-common-service-facade-version}"}</version>
            </dependency>

            <dependency>
                <groupId>com.github.ulisesbocchio</groupId>
                <artifactId>jasypt-spring-boot-starter</artifactId>
                <version>${r"${jasypt.version}"}</version>
            </dependency>

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

            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${r"${mybatis-plus.version}"}</version>
            </dependency>

            <dependency>
                <groupId>cn.hutool</groupId>
                <artifactId>hutool-all</artifactId>
                <version>${r"${hutool.version}"}</version>
            </dependency>

            <!--dubbo-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${r"${dubbo.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-registry-nacos</artifactId>
                <version>${r"${dubbo.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.perf4j</groupId>
                <artifactId>perf4j</artifactId>
                <version>${r"${perf4j.version}"}</version>
            </dependency>

            <dependency>
                <groupId>commons-io</groupId>
                <artifactId>commons-io</artifactId>
                <version>${r"${commons-io.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.bouncycastle</groupId>
                <artifactId>bcprov-jdk15on</artifactId>
                <version>${r"${bcprov-jdk15on.version}"}</version>
            </dependency>

            <!-- oss -->
            <dependency>
                <groupId>com.aliyun.oss</groupId>
                <artifactId>aliyun-sdk-oss</artifactId>
                <version>${r"${aliyun-oss.version}"}</version>
            </dependency>

            <dependency>
                <groupId>org.apache.rocketmq</groupId>
                <artifactId>rocketmq-spring-boot-starter</artifactId>
                <version>${r"${rocketmq.version}"}</version>
            </dependency>

            <!-- knife -->
            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-spring-boot-starter</artifactId>
                <version>${r"${knife.version}"}</version>
            </dependency>

            <!--schedulerx2-->
            <dependency>
                <groupId>com.aliyun.schedulerx</groupId>
                <artifactId>schedulerx2-spring-boot-starter</artifactId>
                <version>${r"${schedulerx2.version}"}</version>
                <!--如果用的是logback，需要把log4j和log4j2排除掉 -->
                <exclusions>
                    <exclusion>
                        <groupId>org.apache.logging.log4j</groupId>
                        <artifactId>log4j-api</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>org.apache.logging.log4j</groupId>
                        <artifactId>log4j-core</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>log4j</groupId>
                        <artifactId>log4j</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>

            <dependency>
                <groupId>com.aliyun.schedulerx</groupId>
                <artifactId>schedulerx2-plugin-kubernetes</artifactId>
                <version>${r"${schedulerx-plugin-kubernetes}"}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- 引入deepinnet-common-boot-starter -->
    ${r"<!-- <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>deepinnet-common-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>com.deepinnet</groupId>
            <artifactId>deepinnet-common-lang</artifactId>
        </dependency>-->"}

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${r"${lombok.version}"}</version>
            <scope>provided</scope>
        </dependency>

        <!--jackson-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${r"${jackson.version}"}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${r"${jackson.version}"}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${r"${jackson.version}"}</version>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${r"${hutool.version}"}</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>${r"${common-lang3.version}"}</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>${r"${commons-collections4.version}"}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.24</version>
                        </path>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${mapstruct.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
</build>

</project>
