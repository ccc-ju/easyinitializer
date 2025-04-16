package com.deepinnet.initializr.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author chenjiaju
 * @since 2025/4/16
 */

@Getter
@AllArgsConstructor
public enum DbTypeEnum {

    MYSQL("mysql", "mysql"),

    POSTGRES("postgres", "postgresql"),

    ;

    private final String code;

    private final String type;

    public static DbTypeEnum getDbTypeEnum(String code){
        for (DbTypeEnum dbTypeEnum : DbTypeEnum.values()) {
            if (dbTypeEnum.getCode().equals(code)) {
                return dbTypeEnum;
            }
        }
        return MYSQL;
    }

}
