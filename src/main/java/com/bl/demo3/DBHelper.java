package com.bl.demo3;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * @Deacription TODO
 * @Author BarryLee
 * @Date 2019/12/12 14:50
 */
public class DBHelper {

  public static Person getPerson(ResultSet resultSet) throws Exception {
    Person person = new Person();
    Field[] fields = person.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      Column anno = field.getAnnotation(Column.class);
      if(anno==null) {
        continue;
      }

      String columnName = anno.value();

      Object object = resultSet.getObject(columnName);
      field.set(person,object);
    }
    return person;
  }

}
