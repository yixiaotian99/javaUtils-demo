package com.my.basic;

/**
 * Created by sunjinwei on 2018/12/3.
 *
 * @author sunjinwei
 * <p>
 * 时间换空间，空间换时间
 * @see https://blog.csdn.net/qq_38455201/article/details/80704654
 */
public class SpaceForTime {

    public static void main(String[] args) {

        SpaceForTime sf = new SpaceForTime();
//        sf.spaceCalc();
        sf.timeCale();
    }


    /**
     * 时间换空间
     * 没有开辟新空间，只是进行了三次计算和三次赋值，空间效率高，时间效率低
     */
    public void spaceCalc() {
        int a = 3;
        int b = 4;
        System.out.println("old：a=" + a + ", b=" + b);

        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("new：a=" + a + ", b=" + b);
    }

    /**
     * 空间换时间
     * 多开辟了一块空间，但是只需要三次赋值即可，时间效率高，空间效率低
     */
    public void timeCale() {
        int a = 3;
        int b = 4;
        System.out.println("old：a=" + a + ", b=" + b);

        int c = a;
        a = b;
        b = c;
        System.out.println("new：a=" + a + ", b=" + b);
    }
}
