package com.my.model;

/**
 * Created by jinwei.sun on 2017/4/27.
 */
public class User {
    private Long id;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User() {
    }
}
