package com.deepinnet.initializr.facade.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.deepinnet.initializr.application.IProjectGenerator;
import com.deepinnet.initializr.converter.ProjectInfoConverter;
import com.deepinnet.initializr.dto.ProjectInitDTO;
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

    private final IProjectGenerator iProjectGenerator;

    @Override
    public void generatorProject(ProjectInitDTO projectInitDTO, HttpServletResponse response) {
        checkParams(projectInitDTO);

        try {
            // 生成项目
            iProjectGenerator.generator(ProjectInfoConverter.convertProjectInitDTO2ProjectInfo(projectInitDTO));

            // 获取目录
            String path = System.getProperty("user.dir") + "/" + projectInitDTO.getProjectName();
            boolean notEmpty = FileUtil.isNotEmpty(new File(path));
            if (!notEmpty) {
                logger.error("项目生成失败, 生成项目目录为null");
                throw new InitializerException("0002", "项目生成失败");
            }

            // 打包压缩包
            ZipUtil.zip(new File(path));

            // 删除文件夹（防止下次生成时有缓存导致压缩文件有问题
            FileUtil.del(path);

            ServletOutputStream outputStream = response.getOutputStream();

            // 下载
            downLoad(projectInitDTO.getProjectName() + ".zip", projectInitDTO, response, path, outputStream);

            // 下载后删除文件
            FileUtil.del(path + ".zip");
        } catch (Exception e) {
            logger.error("项目生成失败, 错误堆栈: ", e);
            throw new InitializerException("0001", "项目生成失败");
        }

    }

    private static void downLoad(String fileName, ProjectInitDTO projectInitDTO, HttpServletResponse response, String resource, ServletOutputStream outputStream) throws IOException {
        BufferedInputStream inputStream = FileUtil.getInputStream(new File(resource + ".zip"));
        byte[] bytes = StreamUtils.copyToByteArray(inputStream);
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        response.addHeader("Content-Length", String.valueOf(bytes.length));
        outputStream.write(bytes);
        outputStream.flush();
    }


    private static void checkParams(ProjectInitDTO projectInitDTO) {
        Assert.notNull(projectInitDTO, "入参不能为空");
        Assert.hasText(projectInitDTO.getProjectName(), "项目名称不能为空");
        Assert.hasText(projectInitDTO.getGroupId(), "项目目录结构不能为空");
    }
}
