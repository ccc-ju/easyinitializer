server:
  port: 8080

spring:
  application:
    name: ${artifactId}
  # nacos
  cloud:
    nacos:
      # 注册中心的配置
      discovery:
        server-addr: http://192.168.3.200:8848
        namespace: dev
        group: DEFAULT_GROUP
        register-enabled: false
      config:
        import-check:
          enabled: false
        server-addr: http://192.168.3.200:8848
        refresh-enabled: true
        namespace: ${r"${spring.cloud.nacos.discovery.namespace}"}
        name: ${r"${spring.application.name}"}
        file-extension: properties
