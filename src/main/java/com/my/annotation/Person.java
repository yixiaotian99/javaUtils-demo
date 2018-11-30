package com.my.annotation;

import java.util.Date;

/**
 * Created by sunjinwei on 2018/11/29.
 *
 * @author sunjinwei
 *
 * @see https://blog.csdn.net/tanyunlong_nice/article/details/53560280
 */
@Table("t_person")
public class Person {

    @Column(name = "id", value = "_id", desc = "数据库自增主键")
    private int id;

    @Column(name = "userName", value = "user_name")
    private String userName;

    @Column(name = "birthDay", value = "birth_day")
    private Date birthDay;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
