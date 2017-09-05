package com.my.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 */
public class RegTest {

    public static void main(String[] args) {

        String str = "This order was placed for QT3000! OK?";
        /**
         * //这种匹配不正确，会解析 \d+ 表示最少有一个数字，那就是 0 其余的被第一个分组匹配了，结果会是这样
         * This order was placed for QT3000! OK?
         * This order was placed for QT300
         * 0
         */
        //String pat = "(.*)(\\d+)(.*)";

        /**
         * 只抽取数字
         * This order was placed for QT3000
         * This order was placed for QT
         * 3000
         */
        String pat = "(\\D*)(\\d+)";

        //调用静态方法 Pattern
        Pattern compile = Pattern.compile(pat);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }

}
