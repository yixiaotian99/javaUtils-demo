package com.my.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by jinwei.sun on 2017/4/27.
 */
public class AA {
    private int id;

    @JSONField(name = "stDate", ordinal = 1)
    private Date startDate;

    //加在属性上，且格式化日期输出
    @JSONField(name = "endDate",format = "yyyyMMdd", ordinal = 2)
    private Date endDate;


    @JSONField(serialize = false)
    private String f0;

    @JSONField(serialize = true, ordinal = 3)
    private String f1;


    //加在 get/set 方法上
    @JSONField(name = "ID")
    public int getId() {
        return id;
    }

    @JSONField(name = "ID")
    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getF0() {
        return f0;
    }

    public void setF0(String f0) {
        this.f0 = f0;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public AA(int id, Date startDate, Date endDate, String f0, String f1) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.f0 = f0;
        this.f1 = f1;
    }

    public AA() {
    }
}
