package com.bl.demo3;

import java.io.Serializable;

/**
 * @Deacription TODO
 * @Author BarryLee
 * @Date 2019/12/12 11:19
 */
public class Person implements Serializable {
  @Column("id")
  private Integer id;

  @Column("username")
  private String username;

  @Column("password")
  private String password;

  public Person() {
  }

  public Person(Integer id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
