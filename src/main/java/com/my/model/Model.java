package com.my.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by jinwei.sun on 2017/4/27.
 */
public class Model {
    private int id;
    private String name;

    @JSONField(serializeUsing = ModelValueSerializer.class)
    private Integer value;

    @JSONField(alternateNames = {"mobile", "email"})
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Model() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", phone='" + phone + '\'' +
                '}';
    }
}
