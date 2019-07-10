package com.touchkiss.mybatis.base.bean;

/**
 * .Bean对象
 *
 * @author Touchkiss
 **/
public class Category implements java.io.Serializable {
  /**  **/
  private Integer id;
  /**  **/
  private Integer parentid;
  /**  **/
  private String name;
  /**  **/
  private Boolean top;

  /**  **/
  public void setId(Integer id) {
    this.id = id;
  }

  /**  **/
  public Integer getId() {
    return this.id;
  }
  /**  **/
  public void setParentid(Integer parentid) {
    this.parentid = parentid;
  }

  /**  **/
  public Integer getParentid() {
    return this.parentid;
  }
  /**  **/
  public void setName(String name) {
    this.name = name;
  }

  /**  **/
  public String getName() {
    return this.name;
  }
  /**  **/
  public void setTop(Boolean top) {
    this.top = top;
  }

  /**  **/
  public Boolean getTop() {
    return this.top;
  }

}