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
    private String name;
    private String keyName;
    private String valueName;
    private Boolean editable;
    private Selector selector;
    private List<SelectOption> options;
}
