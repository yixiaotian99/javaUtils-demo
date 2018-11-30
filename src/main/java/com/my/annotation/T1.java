package com.my.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by sunjinwei on 2018/11/29.
 *
 * @author sunjinwei
 *
 * @see https://blog.csdn.net/tanyunlong_nice/article/details/53560280
 */
@Slf4j
public class T1 {

    public static void main(String[] args) {
        Person person = new Person();
        person.setId(1001);
        person.setUserName("张三");
        person.setBirthDay(new Date());

        queryPerson(person);
    }

    private static String queryPerson(Person person) {
        StringBuffer sbsql = new StringBuffer();

        //1.获取class对象
        Class<? extends Person> c = person.getClass();

        //2.获取到表名
        boolean isTable = c.isAnnotationPresent(Table.class);
        if (!isTable) {
            log.warn("### queryPerson fail, table annotation not exists!");
            return null;
        }
        Table t = c.getAnnotation(Table.class);
        String tableName = t.value();
        sbsql.append("select * from ").append(tableName).append(" where 1=1 ");

        //3. 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {

            //4.处理每一个字段
            boolean isColumn = field.isAnnotationPresent(Column.class);
            if (!isColumn) {
                continue;
            }

            //5.获取属性名
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value(); //字段名
            String fieldName = column.name();
            String desc = column.desc();

            //获取getXX方法  getId  getUserName
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            //columnName:user_name, fieldName:userName, desc:, getMethodName:getUserName
            log.info("### queryPerson get, columnName:{}, fieldName:{}, desc:{}, getMethodName:{}", columnName, fieldName, desc, getMethodName);

            //6.使用反射获取实际值
            Object fieldValue = null;
            try {
                Method method = c.getMethod(getMethodName);
                fieldValue = method.invoke(person);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sbsql.append(" and ").append(fieldName).append("=").append(fieldValue);
        }

        //sql:select * from t_person where 1=1  and id=1001 and userName=张三 and birthDay=Fri Nov 30 10:17:56 CST 2018
        log.info("### queryPerson output, sql:{}", sbsql.toString());
        return sbsql.toString();
    }

}
