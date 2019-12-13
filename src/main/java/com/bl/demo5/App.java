package com.bl.demo5;

import com.bl.db.DBUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Deacription TODO
 * @Author BarryLee
 * @Date 2019/12/12 11:21
 */
@SuppressWarnings("all")
public class App {

  //

  @Test
  public void test() throws Exception {
    Connection conn = DBUtil.getConnection();
    String sql = "select * from person limit 200000";
    PreparedStatement pst = conn.prepareStatement(sql);
    ResultSet resultSet = pst.executeQuery();

    long start = System.currentTimeMillis();
    AbstractEntityHelper entityHelper = EntityHelperFactory.getEntityHelper(Person.class);

    while(resultSet.next()) {
      Person person = (Person)entityHelper.create(resultSet);
    }

    long end = System.currentTimeMillis();
    DBUtil.close(resultSet,pst,conn);

    System.out.println(end-start);
  }
}
