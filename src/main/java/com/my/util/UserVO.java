package com.my.util;

import com.my.model.Company;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapping;

import java.util.Date;

/**
 * Created by sunjinwei on 2018/1/17.
 * 测试VO到DO转换
 */
@Data
public class UserVO {
    private Integer id;                 //唯一主键
    private String name;                //姓名
    private Integer age;                //年龄
    private String gender;              //性别
    private String address;             //住址
    private String nickName;            //昵称
    private Date birthday;              //生日
    private String education;           //学历

    @Mapping(value = "politicalStatus")
    private String status;     //政治面貌，群众、团员、党员等
    private Company company;            //公司


    public static void main(String[] args) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        UserDO userDO = new UserDO();
        userDO.setName("hollis");
        userDO.setAddress("hz");
        userDO.setAge(25);
        userDO.setCompanyId(111);
        userDO.setBirthday(new Date());
        userDO.setGender("male");
        userDO.setEducation("1");
        userDO.setNickName("hollis");
        userDO.setPoliticalStatus("3");

        //UserVO userVO = (UserVO) mapper.map(userDO, UserVO.class);
        UserVO userVO = BeanMapper.map(userDO, UserVO.class);

        System.out.println(userVO);
    }
}
