package com.bl.demo6;

import javassist.*;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription 使用Javassist创建AbstractEntityHelper的子类
 * @Author BarryLee
 * @Date 2019/12/12 16:19
 */
public class EntityHelperFactory {
  private static final Map<Class<?>,AbstractEntityHelper>MAP = new HashMap<>();

  public static AbstractEntityHelper getEntityHelper(Class<?> entityClazz) throws Exception {
    if(entityClazz==null) {
      return null;
    }

    // 如果MAP中已经有了，就直接返回
    AbstractEntityHelper h = MAP.get(entityClazz);
    if(h != null) {
      return h;
    }

    // 获取
    ClassPool pool = ClassPool.getDefault();
    pool.appendSystemPath();

    // 导包
    pool.importPackage(entityClazz.getName());
    pool.importPackage(ResultSet.class.getName());

    // 拿到抽象类
    CtClass abstractCtClass = pool.getCtClass(AbstractEntityHelper.class.getName());

    // 创建类
    String clazzName = entityClazz.getName() + "_Helper";
    CtClass helperClazz = pool.makeClass(clazzName, abstractCtClass);

    // 构造方法
    CtConstructor constructor = new CtConstructor(new CtClass[0], helperClazz);
    constructor.setBody("{}");
    helperClazz.addConstructor(constructor);

    // 构建create方法字符串
    StringBuilder ms = new StringBuilder();
    ms.append("public Object create(ResultSet rs) throws Exception{ \n");
    // 方法体
    Field[] fields = entityClazz.getDeclaredFields();
    ms.append(entityClazz.getName())
        .append(" o = new ")
        .append(entityClazz.getName())
        .append("();\n");

    for (Field field : fields) {
      Column anno = field.getAnnotation(Column.class);
      if(anno==null) {
        continue;
      }

      String fieldName = field.getName();
      String colName = anno.value();
      String funName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);

      if(field.getType()==Integer.class) {
        ms.append("o.set")
            .append(funName)
            .append("(Integer.valueOf(")
            .append("rs.getInt(\"")
            .append(colName)
            .append("\")")
            .append("));\n");
      } else if(field.getType()==String.class) {
        ms.append("o.set")
            .append(funName)
            .append("(")
            .append("rs.getString(\"")
            .append(colName)
            .append("\")")
            .append(");\n");
      }
    }
    ms.append("return o;\n");
    ms.append("}\n");
    //System.out.println(ms.toString());

    // 将方法放到类
    CtMethod method = CtNewMethod.make(ms.toString(), helperClazz);
    helperClazz.addMethod(method);

    // 将类输出到文件看看
    //helperClazz.writeFile("E:\\desktop\\VIP\\HeroStory\\ormTest\\src\\main\\java\\com\\bl\\demo6\\clazzPack");

    Object obj = helperClazz.toClass().newInstance();
    AbstractEntityHelper returnVal = (AbstractEntityHelper) obj;

    // 将结果放到MAP备用
    MAP.put(entityClazz,returnVal);

    return returnVal;
  }
}
