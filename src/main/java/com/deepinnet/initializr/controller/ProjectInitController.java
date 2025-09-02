package com.deepinnet.initializr.controller;

import com.deepinnet.initializr.domain.model.Result;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.dto.SqlConnectionDTO;
import com.deepinnet.initializr.facade.ProjectGeneratorFacade;
import com.deepinnet.initializr.facade.SqlCompareFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    private final SqlCompareFacade sqlCompareFacade;

    @PostMapping("/initGenerate")
    public void initProject(@RequestBody ProjectInitDTO projectInitDTO, HttpServletResponse response) {
        projectGeneratorFacade.generatorProject(projectInitDTO, response);
    }

    /**
     * 测试数据库连接并获取表列表
     */
    @PostMapping("/testConnection")
    public Result<List<String>> testDatabaseConnection(@RequestBody SqlConnectionDTO connectionDTO) {
        List<String> tables = sqlCompareFacade.getTables(connectionDTO);
        return Result.success(tables);
    }

}
