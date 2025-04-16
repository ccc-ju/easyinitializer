package com.deepinnet.initializr.test;

import com.deepinnet.initializr.application.IProjectGenerator;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.dto.SqlConnectionDTO;
import com.deepinnet.initializr.facade.SqlCompareFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目生成测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IProjectGenerator iProjectGenerator;

    @Resource
    private SqlCompareFacade sqlCompareFacade;

    @Test
    public void test_IProjectGenerator() throws Exception {

        ProjectInfo projectInfo = new ProjectInfo(
                "com.deepinnet.espjiaju",
                "espjiaju",
                "1.0.0-SNAPSHOT",
                "espjiaju",
                "Demo project for Spring Boot",
                false,
                false
        );

        iProjectGenerator.generator(projectInfo);
    }

    @Test
    public void getTablesTest(){
        SqlConnectionDTO sqlConnectionDTO = new SqlConnectionDTO();
        sqlConnectionDTO.setDatabaseLink("rm-bp193v7i846v01k442o.mysql.rds.aliyuncs.com:3306/tp_order-env-dev");
        sqlConnectionDTO.setUsername("tp_deepinnet_dev");
        sqlConnectionDTO.setPassword("B%dkLnXRt@nWjeUA");

        List<String> tables = sqlCompareFacade.getTables(sqlConnectionDTO);
        logger.info("tables: {}", tables);
    }

}
