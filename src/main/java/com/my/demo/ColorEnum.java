package com.my.demo;

/**
 * Created by jinwei.sun on 2017/5/31.
 * 自定义函数枚举
 */
public enum ColorEnum {
    RED(1, "red", "红色"),
    BLUE(2, "blue", "红色"),;

    private int index;
    private String name;
    private String desc;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ColorEnum(int index, String name, String desc) {
        this.index = index;
        this.name = name;
        this.desc = desc;
    }

    ColorEnum() {
    }
}
