package com.touchkiss.mybatis.base.bean;

import java.util.Date;
/**
 * 用户组.Bean对象
 *
 * @author Touchkiss
 **/
public class UserGroup implements java.io.Serializable {
  /** id **/
  private Long id;
  /** 组名 **/
  private String groupName;
  /** 修改时间 **/
  private Date lastModifyTime;
  /** 创建时间 **/
  private Date createTime;

  /** id **/
  public void setId(Long id) {
    this.id = id;
  }

  /** id **/
  public Long getId() {
    return this.id;
  }
  /** 组名 **/
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  /** 组名 **/
  public String getGroupName() {
    return this.groupName;
  }
  /** 修改时间 **/
  public void setLastModifyTime(Date lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
  }

  /** 修改时间 **/
  public Date getLastModifyTime() {
    return this.lastModifyTime;
  }
  /** 创建时间 **/
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /** 创建时间 **/
  public Date getCreateTime() {
    return this.createTime;
  }

}