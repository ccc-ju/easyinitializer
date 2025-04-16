package com.deepinnet.initializr.converter;

import cn.hutool.core.util.StrUtil;
import com.deepinnet.initializr.domain.model.ProjectInfo;
import com.deepinnet.initializr.dto.ProjectInitDTO;

/**
 * <p>
 *  转换器
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */
public class ProjectInfoConverter {

    public static ProjectInfo convertProjectInitDTO2ProjectInfo(ProjectInitDTO projectInitDTO) {
        String version = StrUtil.isEmpty(projectInitDTO.getVersion()) ? "1.0.0-SNAPSHOT" : projectInitDTO.getVersion();
        return ProjectInfo.builder()
                .groupId(projectInitDTO.getGroupId())
                .name(projectInitDTO.getProjectName())
                .artifactId(projectInitDTO.getProjectName())
                .version(version)
                .description(projectInitDTO.getDescription())
                .databaseLink(projectInitDTO.getDatabaseLink())
                .username(projectInitDTO.getUsername())
                .password(projectInitDTO.getPassword())
                .enableNacos(projectInitDTO.getEnableNacos())
                .enableDubbo(projectInitDTO.getEnableDubbo()).build();
    }

}
