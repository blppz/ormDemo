package com.bl.demo2;

import com.bl.bean.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Deacription TODO
 * @Author BarryLee
 * @Date 2019/12/12 14:50
 */
public class DBHelper {

  public static Person getPerson(ResultSet resultSet) throws SQLException {
    Person person = new Person();
    person.setId(resultSet.getInt("id"));
    person.setUsername(resultSet.getString("username"));
    person.setPassword(resultSet.getString("password"));
    return person;
  }

  // 这是硬编码，尝试使用反射

}
