package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

public class Cache {
    private String eviction = "FIFO";
    private Long flushInterval = 60000L;
    private Integer size = 512;
    private String type;
    private Boolean readOnly = true;
    private Boolean selectUseCache = true;
    private Boolean insertFlushCache = true;
    private Boolean updateFlushCache = true;
    private Boolean deleteFlushCache = true;

    public Cache() {
    }

    public Cache(String type) {
        this.type = type;
        this.eviction = null;
        this.flushInterval = null;
        this.size = null;
        this.readOnly = null;
    }

    public Cache(String eviction, Long flushInterval, Integer size, Boolean readOnly) {
        this.eviction = eviction;
        this.flushInterval = flushInterval;
        this.size = size;
        this.readOnly = readOnly;
    }

    public Cache(String eviction, Long flushInterval, Integer size, Boolean readOnly, Boolean selectUseCache, Boolean insertFlushCache, Boolean updateFlushCache, Boolean deleteFlushCache) {
        this.eviction = eviction;
        this.flushInterval = flushInterval;
        this.size = size;
        this.readOnly = readOnly;
        this.selectUseCache = selectUseCache;
        this.insertFlushCache = insertFlushCache;
        this.updateFlushCache = updateFlushCache;
        this.deleteFlushCache = deleteFlushCache;
    }

    public String getEviction() {
        return this.eviction;
    }

    public Long getFlushInterval() {
        return this.flushInterval;
    }

    public Integer getSize() {
        return this.size;
    }

    public Boolean getReadOnly() {
        return this.readOnly;
    }

    public Boolean getSelectUseCache() {
        return this.selectUseCache;
    }

    public Boolean getInsertFlushCache() {
        return this.insertFlushCache;
    }

    public Boolean getUpdateFlushCache() {
        return this.updateFlushCache;
    }

    public Boolean getDeleteFlushCache() {
        return this.deleteFlushCache;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}