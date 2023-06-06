package com.deepinnet.initializr.test;

import com.deepinnet.initializr.application.IProjectGenerator;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 项目生成测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IProjectGenerator iProjectGenerator;

    @Test
    public void test_IProjectGenerator() throws Exception {

        ProjectInfo projectInfo = new ProjectInfo(
                "com.deepinnet.espjiaju",
                "espjiaju",
                "1.0.0-SNAPSHOT",
                "espjiaju",
                "Demo project for Spring Boot"
        );

        iProjectGenerator.generator(projectInfo);
    }

}
