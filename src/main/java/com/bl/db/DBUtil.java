package com.bl.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Deacription TODO
 * @Author BarryLee
 * @Date 2019/12/12 14:29
 */
public final class DBUtil {
  private DBUtil() {
  }

  public static Connection getConnection() {
    String driver = "com.mysql.jdbc.Driver";
    String dbName = "root";
    String dbPwd = "barry";
    String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";

    Connection conn = null;
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url,dbName,dbPwd);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return conn;
  }

  public static void close(AutoCloseable ...closeable) {
    for (AutoCloseable ac : closeable) {
      if(ac!=null) {
        try {
          ac.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
