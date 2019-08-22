package com.touchkiss.mybatis.admin.bean;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Author Touchkiss
 * @date 2018/6/8
 */
public class JQueryDatatablesPage {
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<Object> data;
    public JQueryDatatablesPage(){

    }

    public JQueryDatatablesPage(List data) {
        this.data = data;
    }

    public JQueryDatatablesPage(Integer draw, Page page) {
        this.draw = draw;
        this.recordsTotal = page == null ? 0 : page.getTotal();
        this.recordsFiltered = page == null ? 0 : page.getTotal();
        this.data = page == null ? null : page.getResult();
    }

    public JQueryDatatablesPage(Integer draw, Long recordsTotal, Long recordFiltered, List<Object> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordFiltered;
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
