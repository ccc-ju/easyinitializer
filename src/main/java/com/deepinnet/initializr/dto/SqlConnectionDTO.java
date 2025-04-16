package com.deepinnet.initializr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author chenjiaju
 * @since 2024/2/2
 */

@Getter
@Setter
public class SqlConnectionDTO implements Serializable {
    private static final long serialVersionUID = -1355452908118160009L;

    /**
     * 数据库类型 mysql; postgres
     */
    private String dbType;

    /**
     * 数据库连接
     */
    private String databaseLink;

    /**
     * 数据库连接用户名
     */
    private String username;

    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * 表名称
     */
    private String tableName;
}
