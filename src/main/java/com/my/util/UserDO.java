package com.my.util;

import lombok.Data;

import java.util.Date;

/**
 * Created by sunjinwei on 2018/1/17.
 */
@Data
public class UserDO {
    private Integer id;                 //唯一主键
    private Date createdTime;           //创建时间
    private Date updatedTime;           //最后更新时间
    private String name;                //姓名
    private Integer age;                //年龄
    private String gender;              //性别
    private String address;             //住址
    private String password;            //密码
    private String nickName;            //昵称
    private Date birthday;              //生日
    private String education;           //学历
    private String politicalStatus;     //政治面貌,1表示群众,2表示团员,3表示党员,4表示其他,100表示未知
    private Integer companyId;          //公司的ID
    private Integer status;             //数据状态,1表示可用,0表示不可用
}
