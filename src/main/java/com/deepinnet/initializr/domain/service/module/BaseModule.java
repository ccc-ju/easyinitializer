package com.deepinnet.initializr.domain.service.module;

import cn.hutool.core.util.CharsetUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author chenjiaju
 * @since 2023/6/5
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class BaseModule {

    private final static String RESOURCE_PATH  = "generator";

    private final static String FILE = "file";

    private final static String JAR = "jar";

    private static Configuration cfg;

    static {
        URL resourceUrl = ResourceLoader.class.getClassLoader().getResource(RESOURCE_PATH);
        // 非jar包方式载入
        if (resourceUrl != null && resourceUrl.getProtocol().equals(FILE)) {
            try {
                File resourceDir = Paths.get(resourceUrl.toURI()).toFile();
                cfg = new Configuration(Configuration.VERSION_2_3_23);
                cfg.setDirectoryForTemplateLoading(resourceDir);
                cfg.setDefaultEncoding(CharsetUtil.UTF_8);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

        // jar包方式载入
        if (resourceUrl != null && resourceUrl.getProtocol().equals(JAR)) {
            try {
                JarURLConnection jarConnection = (JarURLConnection) resourceUrl.openConnection();
                JarFile jarFile = jarConnection.getJarFile();
                Enumeration<JarEntry> entries = jarFile.entries();

                cfg = new Configuration(Configuration.VERSION_2_3_23);
                StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.isDirectory() && entry.getName().startsWith(RESOURCE_PATH + File.separator) && entry.getName().endsWith(".ftl")) {
                        try (InputStream inputStream = jarFile.getInputStream(entry);
                             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                            String templateContent = readInputStream(inputStreamReader);
                            String templateName = entry.getName().replace(RESOURCE_PATH + File.separator, "");
                            stringTemplateLoader.putTemplate(templateName, templateContent);
                        }
                    }
                }
                cfg.setTemplateLoader(stringTemplateLoader);
                cfg.setDefaultEncoding(CharsetUtil.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String readInputStream(InputStreamReader reader) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            return content.toString();
        }
    }

    protected Template getTemplate(String ftl) throws IOException {
        return cfg.getTemplate(ftl, CharsetUtil.UTF_8);
    }

    protected void writeFile(File file, String ftl, Object dataModel) throws IOException, TemplateException {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(file.toPath()));
        try {
            getTemplate(ftl).process(dataModel, outputStreamWriter);
        } finally {
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
    }

}
