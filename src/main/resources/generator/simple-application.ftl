package ${groupId?replace("-", "")};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ${description}启动类
 *
 * @author system
 * @since ${.now?string("yyyy-MM-dd")}
 */
@SpringBootApplication
public class ${name?replace("-", "")?cap_first}Application {

    public static void main(String[] args) {
        SpringApplication.run(${name?replace("-", "")?cap_first}Application.class, args);
    }

}