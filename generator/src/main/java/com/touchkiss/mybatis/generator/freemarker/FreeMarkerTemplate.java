package com.touchkiss.mybatis.generator.freemarker;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class FreeMarkerTemplate {
    private static final Logger log = LoggerFactory.getLogger(FreeMarkerTemplate.class);
    private static final String ENCODING = "UTF8";
    private static final Configuration cfg;

    public FreeMarkerTemplate() {
    }

    public static String format(String templateName, Map<String, Object> params) {
        StringBuilderWriter out = null;

        String var4;
        try {
            Template template = cfg.getTemplate(templateName, "UTF8");
            out = new StringBuilderWriter();
            template.process(params, out);
            out.flush();
            var4 = out.toString();
        } catch (TemplateException | IOException var13) {
            throw new RuntimeException(var13);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception var12) {
                    log.error("createFile close", var12);
                }
            }

        }

        return var4;
    }

    public static void createFile(String templateName, File outFile, Map<String, Object> params) {
        OutputStreamWriter out = null;

        try {
            Template template = cfg.getTemplate(templateName, "UTF8");
            if (!outFile.getParentFile().exists()) {
                FileUtils.forceMkdir(outFile.getParentFile());
            }

            if (outFile.exists()) {
                outFile.delete();
            }

            out = new OutputStreamWriter(new FileOutputStream(outFile));
            template.process(params, out);
            out.flush();
        } catch (TemplateException | IOException var12) {
            throw new RuntimeException(var12);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var11) {
                    log.error("createFile close", var11);
                }
            }

        }

    }

    static {
        cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setClassicCompatible(true);
        TemplateLoader loader = new ClassTemplateLoader(FreeMarkerTemplate.class, "/template/");
        cfg.setTemplateLoader(loader);
    }
}