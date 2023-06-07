package com.deepinnet.initializr.controller;

import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.facade.ProjectGeneratorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  项目生成工具请求入参
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */

@CrossOrigin
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectInitController {

    private final ProjectGeneratorFacade projectGeneratorFacade;

    @PostMapping("/initGenerate")
    public void initProject(@RequestBody ProjectInitDTO projectInitDTO, HttpServletResponse response) {
        projectGeneratorFacade.generatorProject(projectInitDTO, response);
    }

}
