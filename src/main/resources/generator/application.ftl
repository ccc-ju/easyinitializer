package ${packageName};

<#if enableDubbo>
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
</#if>
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan({"com.deepinnet.${artifactId}.common.dal.dao"})
<#if enableDubbo>
@EnableDubbo(scanBasePackages = "com.deepinnet")
</#if>
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ${className} {

    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }

}