package com.touchkiss.mybatis.base.bean;

import java.util.Date;
/**
 * .Bean对象
 *
 * @author Touchkiss
 **/
public class DTable implements java.io.Serializable {
  /**  **/
  private Integer id;
  /**  **/
  private Date pid;
  /**  **/
  private String name;

  /**  **/
  public void setId(Integer id) {
    this.id = id;
  }

  /**  **/
  public Integer getId() {
    return this.id;
  }
  /**  **/
  public void setPid(Date pid) {
    this.pid = pid;
  }

  /**  **/
  public Date getPid() {
    return this.pid;
  }
  /**  **/
  public void setName(String name) {
    this.name = name;
  }

  /**  **/
  public String getName() {
    return this.name;
  }

}