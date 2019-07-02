package com.touchkiss.mybatis.sqlbuild.condition.original;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class PreparedCondition {
    private String prefix;
    private Object value;
    private String suffix;

    public PreparedCondition() {
    }

    public final PreparedCondition appendPrefix(String prefix) {
        if (this.prefix != null) {
            this.prefix = prefix + this.prefix;
        } else {
            this.prefix = prefix;
        }

        return this;
    }

    public final PreparedCondition appendSuffix(String suffix) {
        if (this.suffix != null) {
            this.suffix = this.suffix + suffix;
        } else {
            this.suffix = suffix;
        }

        return this;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public final PreparedCondition setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public final Object getValue() {
        return this.value;
    }

    public final PreparedCondition setValue(Object value) {
        this.value = value;
        return this;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public final PreparedCondition setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }
}
