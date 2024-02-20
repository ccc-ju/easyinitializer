package com.deepinnet.initializr.controller;

import com.deepinnet.initializr.domain.model.Result;
import com.deepinnet.initializr.dto.SqlConnectionDTO;
import com.deepinnet.initializr.facade.SqlCompareFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  sql比对
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */

@CrossOrigin
@RestController
@Api(tags = "sql对比")
@RequestMapping("/compare")
@RequiredArgsConstructor
public class SqlCompareController {

    private final SqlCompareFacade sqlCompareFacade;

    @ApiOperation("[sql对比] => 获取数据库表")
    @PostMapping("/getTables")
    public Result<List<String>> getTables(@RequestBody SqlConnectionDTO sqlConnection) {
        List<String> tables = sqlCompareFacade.getTables(sqlConnection);
        return Result.success(tables);
    }

}
