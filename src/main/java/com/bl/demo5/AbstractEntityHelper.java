package com.bl.demo5;

import java.sql.ResultSet;

/**
 * @Deacription 相当于前面的DBHelper的一层抽象
 * @Author BarryLee
 * @Date 2019/12/12 16:18
 */
public abstract class AbstractEntityHelper {
  public abstract Object create(ResultSet rs) throws Exception;
}
