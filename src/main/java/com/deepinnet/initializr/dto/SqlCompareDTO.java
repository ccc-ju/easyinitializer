package com.deepinnet.initializr.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  sql对比
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */

@Getter
@Setter
public class SqlCompareDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    List<SqlConnectionDTO> sqlConnections;

}
