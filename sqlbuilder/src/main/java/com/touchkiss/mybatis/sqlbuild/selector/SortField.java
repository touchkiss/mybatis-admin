package com.touchkiss.mybatis.sqlbuild.selector;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import com.touchkiss.mybatis.sqlbuild.keyword.Sort;

public class SortField extends Field {
    private Sort orderEnum;

    public SortField(Table table, String field, Sort order) {
        super(table, field);
        this.orderEnum = Sort.DESC;
        this.orderEnum = order;
    }

    public SortField(String field, Sort order) {
        super(field);
        this.orderEnum = Sort.DESC;
        this.orderEnum = order;
    }

    public SortField(String field) {
        super(field);
        this.orderEnum = Sort.DESC;
    }

    public Sort getOrderEnum() {
        return this.orderEnum;
    }

    public String getOrder() {
        return this.orderEnum.name();
    }

    public void setOrderEnum(Sort orderEnum) {
        this.orderEnum = orderEnum;
    }
}
