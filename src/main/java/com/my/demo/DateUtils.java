package com.my.demo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by jinwei.sun on 2017/4/27.
 */
public class DateUtils {

    /**
     * 应用 java1.8 计算前后天数
     */
    public void dateBetween(){
        LocalDate now = LocalDate.now(); //本地日期
        System.out.println("本地日期: " + now); //2017-04-28

        LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS);
        System.out.println("明天日期：" + tomorrow); //2017-04-29

        LocalDate yesterday = now.plus(-1, ChronoUnit.DAYS);
        System.out.println("昨天日期：" + yesterday); //2017-04-27
    }

}
