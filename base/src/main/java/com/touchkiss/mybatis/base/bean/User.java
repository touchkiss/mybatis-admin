package com.touchkiss.mybatis.base.bean;

/**
 * .Bean对象
 *
 * @author Touchkiss
 **/
public class User implements java.io.Serializable {
  /**  **/
  private Integer uid;
  /**  **/
  private String username;
  /**  **/
  private String password;

  /**  **/
  public void setUid(Integer uid) {
    this.uid = uid;
  }

  /**  **/
  public Integer getUid() {
    return this.uid;
  }
  /**  **/
  public void setUsername(String username) {
    this.username = username;
  }

  /**  **/
  public String getUsername() {
    return this.username;
  }
  /**  **/
  public void setPassword(String password) {
    this.password = password;
  }

  /**  **/
  public String getPassword() {
    return this.password;
  }

}