package com.deepinnet.initializr.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.deepinnet.initializr.converter.ProjectInfoConverter;
import com.deepinnet.initializr.domain.enums.InitializerErrorCode;
import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import com.deepinnet.initializr.domain.service.SimpleProjectGeneratorImpl;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.exception.InitializerException;
import com.deepinnet.initializr.infrastructure.utils.MybatisPlusGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 普通项目生成处理器
 * 
 * @author system
 * @since 2025/09/02
 */
@Service
public class InitializerSimpleGeneratorHandler implements InitializerGeneratorHandler {

    private final Logger logger = LoggerFactory.getLogger(InitializerSimpleGeneratorHandler.class);

    private final SimpleProjectGeneratorImpl simpleProjectGenerator;

    public InitializerSimpleGeneratorHandler(@Qualifier("simpleProjectGenerator") SimpleProjectGeneratorImpl simpleProjectGenerator) {
        this.simpleProjectGenerator = simpleProjectGenerator;
    }

    @Override
    public InitializerTypeEnum supportOperation() {
        return InitializerTypeEnum.SIMPLE_PROJECT;
    }

    @Override
    public void handle(ProjectInitDTO projectInitDTO) {
        try {
            // 生成项目
            simpleProjectGenerator.generator(ProjectInfoConverter.convertProjectInitDTO2ProjectInfo(projectInitDTO));

            boolean notEmpty = FileUtil.isNotEmpty(new File(projectInitDTO.getPath()));
            if (!notEmpty) {
                logger.error("普通项目生成失败, 生成项目目录为null");
                throw new InitializerException("0001", "普通项目生成失败");
            }

            if (StrUtil.isNotBlank(projectInitDTO.getDatabaseLink())
                    && StrUtil.isNotBlank(projectInitDTO.getUsername())
                    && StrUtil.isNotBlank(projectInitDTO.getPassword())) {
                // mybatis-plus-generator生成do及相应的基础代码
                MybatisPlusGenerator.generator(projectInitDTO, projectInitDTO.getPath());
            }
        } catch (Exception e) {
            logger.error("simple project generator error: ", e);
            throw new InitializerException(InitializerErrorCode.GENERATION_ERROR.getErrorCode(), InitializerErrorCode.GENERATION_ERROR.getErrorCode());
        }
    }
}