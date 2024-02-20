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
 * @since 2024/2/20
 */

@Getter
@Setter
public class SqlCompareConnectionDTO implements Serializable {
    private static final long serialVersionUID = 5362860943071109218L;

    private SqlConnectionDTO sourceConnection;

    private SqlConnectionDTO targetConnection;

}
