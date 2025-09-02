package com.deepinnet.initializr.facade.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.deepinnet.initializr.domain.enums.InitializerErrorCode;
import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.engine.InitializerEngine;
import com.deepinnet.initializr.exception.InitializerException;
import com.deepinnet.initializr.facade.ProjectGeneratorFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  项目生成facadeImpl
 * </p>
 *
 * @author chenjiaju
 * @since 2023/6/6
 */

@Service
@RequiredArgsConstructor
public class ProjectGeneratorFacadeImpl implements ProjectGeneratorFacade {

    private final Logger logger = LoggerFactory.getLogger(ProjectGeneratorFacadeImpl.class);

    private final InitializerEngine initializerEngine;

    @Override
    public void generatorProject(ProjectInitDTO projectInitDTO, HttpServletResponse response) {
        checkParams(projectInitDTO);

        // 给定项目目录
        projectInitDTO.setPath(System.getProperty("user.dir") + "/" + projectInitDTO.getProjectName());

        // 根据项目类型选择相应的处理器生成项目结构及相应的基础代码
        InitializerTypeEnum projectType = projectInitDTO.getProjectType() != null ? 
            projectInitDTO.getProjectType() : InitializerTypeEnum.DEEPINNET;
        initializerEngine.execute(projectInitDTO, projectType);

        // 项目下载
        zipDownload(projectInitDTO, response);
    }

    private void zipDownload(ProjectInitDTO projectInitDTO, HttpServletResponse response) {
        try {
            // 打包压缩包
            ZipUtil.zip(new File(projectInitDTO.getPath()));

            // 删除文件夹（防止下次生成时有缓存导致压缩文件有问题
            FileUtil.del(projectInitDTO.getPath());

            ServletOutputStream outputStream = response.getOutputStream();

            // 下载
            download(projectInitDTO.getProjectName() + ".zip", response, projectInitDTO.getPath(), outputStream);

            // 下载后删除文件
            FileUtil.del(projectInitDTO.getPath() + ".zip");
        } catch (IOException e) {
            logger.error("download error");
            throw new InitializerException(InitializerErrorCode.DOWN_LOAD_ERROR.getErrorCode(), InitializerErrorCode.DOWN_LOAD_ERROR.getErrorCode());
        }
    }

    private void download(String fileName, HttpServletResponse response, String resource, ServletOutputStream outputStream) throws IOException {
        BufferedInputStream inputStream = FileUtil.getInputStream(new File(resource + ".zip"));
        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        response.addHeader("Content-Length", String.valueOf(bytes.length));
        outputStream.write(bytes);
        outputStream.flush();
    }

    private void checkParams(ProjectInitDTO projectInitDTO) {
        Assert.notNull(projectInitDTO, "入参不能为空");
        Assert.hasText(projectInitDTO.getProjectName(), "项目名称不能为空");
        Assert.hasText(projectInitDTO.getGroupId(), "项目目录结构不能为空");
    }
}
