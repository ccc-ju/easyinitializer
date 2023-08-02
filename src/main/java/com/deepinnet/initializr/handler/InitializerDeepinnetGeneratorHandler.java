package com.deepinnet.initializr.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.deepinnet.initializr.application.IProjectGenerator;
import com.deepinnet.initializr.converter.ProjectInfoConverter;
import com.deepinnet.initializr.domain.enums.InitializerErrorCode;
import com.deepinnet.initializr.domain.enums.InitializerTypeEnum;
import com.deepinnet.initializr.dto.ProjectInitDTO;
import com.deepinnet.initializr.exception.InitializerException;
import com.deepinnet.initializr.infrastructure.utils.MybatisPlusGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;

/**
 *
 * @author chenjiaju
 * @since 2023/6/14
 */

@Service
@RequiredArgsConstructor
public class InitializerDeepinnetGeneratorHandler implements InitializerGeneratorHandler {

    private final Logger logger = LoggerFactory.getLogger(InitializerDeepinnetGeneratorHandler.class);

    private final IProjectGenerator iProjectGenerator;

    @Override
    public InitializerTypeEnum supportOperation() {
        return InitializerTypeEnum.DEEPINNET;
    }

    @Override
    public void handle(ProjectInitDTO projectInitDTO) {
        try {
            // 生成项目
            iProjectGenerator.generator(ProjectInfoConverter.convertProjectInitDTO2ProjectInfo(projectInitDTO));

            boolean notEmpty = FileUtil.isNotEmpty(new File(projectInitDTO.getPath()));
            if (!notEmpty) {
                logger.error("项目生成失败, 生成项目目录为null");
                throw new InitializerException("0001", "项目生成失败");
            }

            if (StrUtil.isNotBlank(projectInitDTO.getDatabaseLink())
                    && StrUtil.isNotBlank(projectInitDTO.getUsername())
                    && StrUtil.isNotBlank(projectInitDTO.getPassword())) {
                // mybatis-plus-generator生成do及相应的基础代码
                MybatisPlusGenerator.generator(projectInitDTO, projectInitDTO.getPath());
            }
        } catch (Exception e) {
            logger.error("project generator error: ", e);
            throw new InitializerException(InitializerErrorCode.GENERATION_ERROR.getErrorCode(), InitializerErrorCode.GENERATION_ERROR.getErrorCode());
        }
    }

}
