package com.deepinnet.initializr.infrastructure.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
public class TemplateUtils {

    /**
     * freemarker 配置
     */
    private static Configuration cfg = new Configuration();
    ;

    /**
     * 获取模板跟地址
     *
     * @return
     */
    public static URL getTemplatePath() {
        return TemplateUtils.class.getResource("");
    }

    /**
     * 获取 freemarker 配置
     *
     * @return
     * @throws IOException
     */
    public static Configuration getConfiguration() throws IOException {
        cfg.setDirectoryForTemplateLoading(new File(getTemplatePath().getPath()));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
    }

}
