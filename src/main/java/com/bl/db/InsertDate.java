package com.bl.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @Deacription TODO
 * @Author BarryLee
 * @Date 2019/12/12 11:21
 */
public class InsertDate {
  @Test
  public void insert() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    String dbUsername = "root";
    String dbPassword = "barry";
    String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

    Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
    PreparedStatement pst = connection.prepareStatement("insert into person(username,password) values(?,?)");

    for(int i = 0;i<200000;i++) {
      String username = "jack" + i;
      String password = "123" + i;
      pst.setObject(1,username);
      pst.setObject(2,password);
      pst.executeBatch(); // 这个快一千倍。。
    }

    pst.close();
    connection.close();
  }
}
