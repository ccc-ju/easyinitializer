package com.deepinnet.initializr.facade;

import com.deepinnet.initializr.dto.ProjectInitDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  项目生成facade
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */
public interface ProjectGeneratorFacade {

    /**
     * 生成项目
     * @param projectInitDTO 入参
     */
    void generatorProject(ProjectInitDTO projectInitDTO, HttpServletResponse response);

}
