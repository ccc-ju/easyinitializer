package ${groupId?replace("-", "")};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.mybatis.spring.annotation.MapperScan;

/**
 * ${description}启动类
 *
 * @author system
 * @since ${.now?string("yyyy-MM-dd")}
 */
@SpringBootApplication
<#if dbType?? && dbType != "">
@MapperScan({"${groupId?replace("-", "") + ".dal.dao"}"})
@ComponentScan(value = {"com.deepinnet"}, excludeFilters = @ComponentScan.Filter(
    type = FilterType.ASSIGNABLE_TYPE
))
</#if>
public class ${name?replace("-", "")?cap_first}Application {

    public static void main(String[] args) {
        SpringApplication.run(${name?replace("-", "")?cap_first}Application.class, args);
    }

}