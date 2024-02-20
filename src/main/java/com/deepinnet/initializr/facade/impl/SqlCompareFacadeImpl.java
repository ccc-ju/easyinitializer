package com.deepinnet.initializr.facade.impl;

import cn.hutool.core.collection.ListUtil;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.dto.SqlCompareConnectionDTO;
import com.deepinnet.initializr.dto.SqlConnectionDTO;
import com.deepinnet.initializr.facade.SqlCompareFacade;
import com.deepinnet.initializr.infrastructure.utils.DataBaseUtil;
import com.deepinnet.initializr.infrastructure.utils.MybatisPlusGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  sql对比
 * </p>
 *
 * @author chenjiaju
 * @since 2024/2/2
 */

@Service
@RequiredArgsConstructor
public class SqlCompareFacadeImpl implements SqlCompareFacade {

    private final Logger logger = LoggerFactory.getLogger(SqlCompareFacadeImpl.class);

    @Override
    public List<String> getTables(SqlConnectionDTO sqlConnection) {
        checkParams(sqlConnection);

        String[] tableNames = DataBaseUtil.getTableNames(sqlConnection.getDatabaseLink(), sqlConnection.getUsername(), sqlConnection.getPassword());

        return ListUtil.of(tableNames);
    }

    @Override
    public String compareTable(SqlCompareConnectionDTO dto) {
        checkCompareTableParams(dto);

        return null;
    }

    private static void checkCompareTableParams(SqlCompareConnectionDTO dto) {
        Assert.notNull(dto, "sqlConnections不能为空");
        Assert.notNull(dto.getSourceConnection(), "sourceConnection不能为空");
        Assert.notNull(dto.getTargetConnection(), "targetConnection不能为空");
        checkParams(dto.getSourceConnection());
        checkParams(dto.getTargetConnection());
        Assert.hasText(dto.getSourceConnection().getTableName(), "source tableName不能为空");
        Assert.hasText(dto.getTargetConnection().getTableName(), "target tableName不能为空");
    }

    private ProjectInitDTO buildParams(SqlConnectionDTO sqlConnection) {
        ProjectInitDTO projectInitDTO = new ProjectInitDTO();
        projectInitDTO.setDatabaseLink(sqlConnection.getDatabaseLink());
        projectInitDTO.setUsername(sqlConnection.getUsername());
        projectInitDTO.setPassword(sqlConnection.getPassword());
        return projectInitDTO;
    }

    private static void checkParams(SqlConnectionDTO sqlConnection) {
        Assert.notNull(sqlConnection, "sqlConnection is null");
        Assert.hasText(sqlConnection.getDatabaseLink(), "databaseLink is null");
        Assert.hasText(sqlConnection.getUsername(), "database username is null");
        Assert.hasText(sqlConnection.getPassword(), "database password is null");
    }
}
