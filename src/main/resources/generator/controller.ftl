package com.deepinnet.${artifactId}.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class DemoController {

    @ApiOperation("[测试接口] => 测试接口")
    @GetMapping("/v1")
    public String getSubPolicyList() {
        return "接口访问成功！";
    }

}
