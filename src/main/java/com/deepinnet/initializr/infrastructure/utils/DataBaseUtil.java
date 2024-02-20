package com.deepinnet.initializr.infrastructure.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.deepinnet.initializr.exception.InitializerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  数据库工具类
 * </p>
 *
 * @author chenjiaju
 * @since 2024/2/2
 */
public class DataBaseUtil {

    /**
     * 获取数据库表名
     * @param databaseLink 数据库连接
     * @param username 用户名
     * @param password 密码
     * @return 数据库表名
     */
    public static String[] getTableNames(String databaseLink, String username, String password) {
        String dataBaseName = databaseLink.substring(databaseLink.lastIndexOf("/") + 1);
        ResultSet resultSet = null;
        Connection connection = null;
        List<String> tableList = new ArrayList<>();
        try {
            String url = "jdbc:mysql://" + databaseLink + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(url, username, password);
            // 拿数据库元数据
            DatabaseMetaData metaData = connection.getMetaData();

            // 获取所有表名
            resultSet = metaData.getTables(dataBaseName, null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tableList.add(tableName);
            }
            return ArrayUtil.toArray(tableList, String.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InitializerException("0005", "数据库连接异常");
        } finally {
            try {
                if (ObjectUtil.isNotNull(resultSet)) {
                    resultSet.close();
                }
                if (ObjectUtil.isNotNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取数据库结构
     * @param databaseLink 数据库连接
     * @param username 用户名
     * @param password 密码
     * @return 数据库表名
     */
    public static Map<String, String> getTableColumns(String databaseLink, String username, String password, String tableName) {
        String dataBaseName = databaseLink.substring(databaseLink.lastIndexOf("/") + 1);
        ResultSet resultSet = null;
        Connection connection = null;
        List<String> tableList = new ArrayList<>();
        try {
            String url = "jdbc:mysql://" + databaseLink + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(url, username, password);
            // 拿数据库元数据
            DatabaseMetaData metaData = connection.getMetaData();

            return getColumns(metaData, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InitializerException("0005", "数据库连接异常");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitializerException("0006", "数据库字段获取异常");
        } finally {
            try {
                if (ObjectUtil.isNotNull(resultSet)) {
                    resultSet.close();
                }
                if (ObjectUtil.isNotNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private static Map<String, String> getColumns(DatabaseMetaData dbMetaData, String tableName) throws Exception {
        Map<String, String> columns = new HashMap<>();
        try (ResultSet rs = dbMetaData.getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String columnType = rs.getString("TYPE_NAME");
                columns.put(columnName, columnType);
            }
        }
        return columns;
    }

}
