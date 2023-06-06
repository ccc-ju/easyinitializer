package com.deepinnet.initializr.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  项目生成入参描述
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */

@Data
public class ProjectInitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目坐标地址 (com.deepinnet.xxx)
     */
    private String groupId;

    /**
     * 项目项目名称
     */
    private String projectName;

    /**
     * 项目版本 1.0.0-SNAPSHOT
     */
    private String version;

    /**
     * 项目描述
     */
    private String description;

}
