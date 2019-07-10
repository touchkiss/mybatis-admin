package com.touchkiss.mybatis.generator.template;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import java.beans.ConstructorProperties;
import java.io.File;

public class GeneratorResult {
    private File file;
    private String text;
    private String packageClassName;

    public File getFile() {
        return this.file;
    }

    public String getText() {
        return this.text;
    }

    public String getPackageClassName() {
        return this.packageClassName;
    }

    @ConstructorProperties({"file", "text", "packageClassName"})
    public GeneratorResult(File file, String text, String packageClassName) {
        this.file = file;
        this.text = text;
        this.packageClassName = packageClassName;
    }
}
