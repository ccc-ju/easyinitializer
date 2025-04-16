package com.deepinnet.initializr.infrastructure.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.deepinnet.initializr.dto.SqlCompareConnectionDTO;
import com.deepinnet.initializr.dto.SqlConnectionDTO;
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

    public static void main(String[] args) {
        SqlCompareConnectionDTO sqlCompareConnectionDTO = new SqlCompareConnectionDTO();
        SqlConnectionDTO source = new SqlConnectionDTO();
        source.setDatabaseLink("localhost:3306/tp_order-env-dev");
        source.setUsername("tp_deepinnet_dev");
        source.setPassword("B%dkLnXRt@nWjeUA");
        source.setTableName("tp_order");

        SqlConnectionDTO target = new SqlConnectionDTO();
        target.setDatabaseLink("localhost:3306/tp_order-env-test");
        target.setUsername("tp_deepinnet_test");
        target.setPassword("20230424MnfFfdWZsyH2KvuF");
        target.setTableName("tp_order");

        sqlCompareConnectionDTO.setSourceConnection(source);
        sqlCompareConnectionDTO.setTargetConnection(target);

        String result = compareTableStructure(sqlCompareConnectionDTO);
        System.out.println(result);
    }

    /**
     * 比较表结构
     * @param dto 连接参数
     */
    private static String compareTableStructure(SqlCompareConnectionDTO dto) {
        SqlConnectionDTO sourceConnection = dto.getSourceConnection();
        SqlConnectionDTO targetConnection = dto.getTargetConnection();
        StringBuilder sb = new StringBuilder();

        // 获取并比较列
        Map<String, String> sourceTableColumns = getTableColumns(sourceConnection.getDatabaseLink(), sourceConnection.getUsername(), sourceConnection.getPassword(), sourceConnection.getTableName());
        Map<String, String> targetTableColumns = getTableColumns(targetConnection.getDatabaseLink(), targetConnection.getUsername(), targetConnection.getPassword(), targetConnection.getTableName());
        sb.append(compareColumns(dto.getSourceConnection().getTableName(), dto.getTargetConnection().getTableName(), sourceTableColumns, targetTableColumns));

        // 获取并比较索引
        Map<String, Boolean> sourceIndexes = getIndexesMap(sourceConnection.getDatabaseLink(), sourceConnection.getUsername(), sourceConnection.getPassword(), sourceConnection.getTableName());
        Map<String, Boolean> targetIndexes = getIndexesMap(targetConnection.getDatabaseLink(), targetConnection.getUsername(), targetConnection.getPassword(), targetConnection.getTableName());
        sb.append(compareIndexes(dto.getSourceConnection().getTableName(), dto.getTargetConnection().getTableName(), sourceIndexes, targetIndexes));

        return sb.toString();
    }

    /**
     * 比较表字段
     * @param source 原始表
     * @param target 需要比对的目标表
     * @param sourceTableColumns 原始表字段
     * @param targetTableColumns 目标表字段
     * @return 比较结果
     */
    private static String compareColumns(String source, String target, Map<String, String> sourceTableColumns, Map<String, String> targetTableColumns) {
        StringBuilder sb = new StringBuilder();
        // 比较table1中存在而table2中不存在的列
        for (String column : sourceTableColumns.keySet()) {
            if (!targetTableColumns.containsKey(column)) {
                sb.append("ALTER TABLE ").append(target).append(" ADD ").append(column).append(" ").append(sourceTableColumns.get(column)).append(";");
            }
        }

        // 比较table2中存在而table1中不存在的列
        for (String column : targetTableColumns.keySet()) {
            if (!sourceTableColumns.containsKey(column)) {
                sb.append("ALTER TABLE ").append(source).append(" ADD ").append(column).append(" ").append(targetTableColumns.get(column)).append(";");
            }
        }

        return sb.toString();
    }

    /**
     * 比较表索引
     * @param source 原始表
     * @param target 需要比对的目标表
     * @param sourceTableIndexes 原始表索引
     * @param targetTableIndexes 目标表索引
     */
    private static String compareIndexes(String source, String target, Map<String, Boolean> sourceTableIndexes, Map<String, Boolean> targetTableIndexes) {
        StringBuilder sb = new StringBuilder();
        // 比较table1中存在而table2中不存在的索引
        for (String index : sourceTableIndexes.keySet()) {
            if (!targetTableIndexes.containsKey(index)) {
                // 需要指定具体的列名
                sb.append("CREATE INDEX ").append(index).append(" ON ").append(target).append("(...);");
            }
        }

        // 比较table2中存在而table1中不存在的索引
        for (String index : targetTableIndexes.keySet()) {
            if (!sourceTableIndexes.containsKey(index)) {
                sb.append("DROP INDEX ").append(index).append(" ON ").append(target).append(";");
            }
        }
        return sb.toString();
    }

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
     * 获取数据库表名
     * @param databaseLink 数据库连接
     * @param username 用户名
     * @param password 密码
     * @return 数据库表名
     */
    public static String[] getTableNames(String databaseLink, String username, String password, String dbType) {
        String dataBaseName = databaseLink.substring(databaseLink.lastIndexOf("/") + 1);
        ResultSet resultSet = null;
        Connection connection = null;
        List<String> tableList = new ArrayList<>();
        try {
            String url = "jdbc:" + dbType +"://" + databaseLink;
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

    /**
     * 获取数据库索引
     * @param databaseLink 数据库连接
     * @param username 用户名
     * @param password 密码
     * @return 数据库表名
     */
    public static Map<String, Boolean> getIndexesMap(String databaseLink, String username, String password, String tableName) {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String url = "jdbc:mysql://" + databaseLink + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
            connection = DriverManager.getConnection(url, username, password);
            // 拿数据库元数据
            DatabaseMetaData metaData = connection.getMetaData();

            return getIndexes(metaData, tableName);
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

    private static Map<String, Boolean> getIndexes(DatabaseMetaData dbMetaData, String tableName) throws Exception {
        Map<String, Boolean> indexes = new HashMap<>();
        try (ResultSet rs = dbMetaData.getIndexInfo(null, null, tableName, false, false)) {
            while (rs.next()) {
                String indexName = rs.getString("INDEX_NAME");
                boolean isUnique = !rs.getBoolean("NON_UNIQUE");
                if (indexName != null) {
                    // 忽略表的主键索引
                    indexes.put(indexName, isUnique);
                }
            }
        }
        return indexes;
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
