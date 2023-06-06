spring:
  redis:
    host: 192.168.3.200
    port: 6379
    password: ENC(ao+A54rgdnsZ/wIydpBlnxYqM34u5B4O)
  #=======druid数据源配置=======
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      #  公网地址
      url: jdbc:mysql://127.0.0.1:3306/esp-policy-env-dev?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
      #  内网地址
      #url: jdbc:mysql://rm-bp193v7i846v01k44.mysql.rds.aliyuncs.com:3306/ins?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
      username: 123456
      password: 123456
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
      filters: stat,wall
      initialSize: 5
      keepAlive: true
      maxActive: 20
      maxPoolPreparedStatementPerConnectionSize: 20
      maxWait: 60000
      minEvictableIdleTimeMillis: 300000
      minIdle: 5
      poolPreparedStatements: true
      testOnBorrow: true
      testOnReturn: true
      testWhileIdle: true
      timeBetweenEvictionRunsMillis: 60000
      useGlobalDataSourceStat: true
      validationQuery: SELECT 1
      stat-view-servlet:
        enabled: true
        url-pattern: /druid/*

# dubbo
dubbo:
  application:
    name: ${r"${spring.application.name}"}
  registry:
    address: nacos://192.168.3.200:8848
    parameters:
      namespace: ${r"${spring.cloud.nacos.discovery.namespace}"}
  protocol:
    port: 20880
  provider:
    timeout: 3000
    retries: 0
    check: false
  consumer:
    timeout: 3000
    retries: 0
    check: false

mybatis-plus:
  mapper-locations: classpath*:mybatis/mapper/*Mapper.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  sql:
    print: true

# 打印日志的配置
logging:
  level:
    com:
      alibaba:
        cloud:
          nacos:
            client: debug

oss:
endpoint: oss-cn-hangzhou.aliyuncs.com