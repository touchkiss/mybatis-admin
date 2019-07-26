package com.touchkiss.mybatis.admin.bean;

import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import lombok.Data;

import java.util.List;

/**
 * @Author Touchkiss
 * @create: 2019-07-05 12:38
 */
@Data
public class ForeignKeyInfo {
    /**
     * 外键表名
     */
    private String name;
    /**
     * 键-列名
     */
    private String keyName;
    /**
     * 值-列名
     */
    private String valueName;
    /**
     * 是否可编辑
     */
    private Boolean editable;
    /**
     * 传入selector
     */
    private Selector selector;
    /**
     * 传入option列表
     */
    private List<SelectOption> options;
}
