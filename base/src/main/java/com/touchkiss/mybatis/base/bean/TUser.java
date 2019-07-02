package com.touchkiss.mybatis.base.bean;

import java.util.Date;
/**
 * 用户表.Bean对象
 *
 * @author Touchkiss
 **/
public class TUser implements java.io.Serializable {
  /**  **/
  private Long id;
  /**  **/
  private Long groupId;
  /**  **/
  private String userName;
  /**  **/
  private Integer age;
  /**  **/
  private Date lastModifyTime;
  /**  **/
  private Date createTime;

  /**  **/
  public void setId(Long id) {
    this.id = id;
  }

  /**  **/
  public Long getId() {
    return this.id;
  }
  /**  **/
  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  /**  **/
  public Long getGroupId() {
    return this.groupId;
  }
  /**  **/
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**  **/
  public String getUserName() {
    return this.userName;
  }
  /**  **/
  public void setAge(Integer age) {
    this.age = age;
  }

  /**  **/
  public Integer getAge() {
    return this.age;
  }
  /**  **/
  public void setLastModifyTime(Date lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
  }

  /**  **/
  public Date getLastModifyTime() {
    return this.lastModifyTime;
  }
  /**  **/
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**  **/
  public Date getCreateTime() {
    return this.createTime;
  }

}