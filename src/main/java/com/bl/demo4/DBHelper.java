package com.bl.demo4;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * @Deacription 在demo3的基础上改为通用的
 * @Author BarryLee
 * @Date 2019/12/12 14:50
 */
@SuppressWarnings("all")
public class DBHelper {

  public static <T> T getPerson(ResultSet resultSet,Class<T> clazz) throws Exception {
    Field[] fields = clazz.getDeclaredFields();
    Object obj = clazz.newInstance();

    for (Field field : fields) {
      field.setAccessible(true);
      Column anno = field.getAnnotation(Column.class);
      if(anno==null) {
        continue;
      }

      String columnName = anno.value();

      Object val = resultSet.getObject(columnName);
      field.set(obj,val);
    }

    return (T)obj;
  }

}
