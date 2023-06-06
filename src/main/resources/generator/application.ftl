package ${packageName};

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan({"com.deepinnet.${artifactId}.common.dal.dao"})
@EnableDubbo(scanBasePackages = "com.deepinnet")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ${className} {

    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }

}