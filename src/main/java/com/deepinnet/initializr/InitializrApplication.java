package com.deepinnet.initializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class InitializrApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitializrApplication.class, args);
    }

}
