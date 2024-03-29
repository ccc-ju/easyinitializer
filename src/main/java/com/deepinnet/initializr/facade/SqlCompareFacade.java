package com.deepinnet.initializr.facade;

import com.deepinnet.initializr.dto.SqlCompareConnectionDTO;
import com.deepinnet.initializr.dto.SqlConnectionDTO;

import java.util.List;

/**
 * <p>
 *  sql对比facade
 * </p>
 *
 * @author chenjiaju
 * @since 2024/2/2
 */
public interface SqlCompareFacade {

    /**
     * 获取当前所有表名
     * @param sqlConnection sql连接参数
     * @return 表名
     */
    List<String> getTables(SqlConnectionDTO sqlConnection);

    /**
     * 对比两张表结构
     * @param dto 连接参数
     * @return 比对结果
     */
    String compareTable(SqlCompareConnectionDTO dto);
}
