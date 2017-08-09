package com.my.demo;

import org.iherus.codegen.bean.QrcodeConfig;
import org.iherus.codegen.qrcode.SimpleQrcodeGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 生成二维码
 */
public class QrcgenCode {

    /**
     * 根据 url 直接输出到图片
     */
    public static void writeToFile() throws IOException {
        String url = "http://git.oschina.net/dashboard/projects"; //扫描二维码跳转的地址
        new SimpleQrcodeGenerator().generate(url).toFile("F:\\qrcode\\test1.png");
    }


    /**
     * 输出到流
     */
    public static void writeToStream() throws Exception {
        String url = "http://blog.csdn.net/historyasamirror/article/details/6245157"; //扫描二维码跳转的地址
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\qrcode\\test2.png");
        new SimpleQrcodeGenerator().generate(url).toStream(fileOutputStream);
    }


    /**
     * 添加本地的 logo 小图片
     */
    public static void addLogoToFile() throws IOException {
        String url = "http://jiantuku.com/";
        new SimpleQrcodeGenerator().setLogo("F:\\qrcode\\logo.png").generate(url).toFile("F:\\qrcode\\test3.png");
    }

    /**
     * 添加在线 logo 图片
     */
    public static void addOnlineLogoToFile() throws IOException {
        String url = "https://github.com/";
        String logoUrl = "http://www.demlution.com/site_media/media/photos/2014/11/06/3JmYoueyyxS4q4FcxcavgJ.jpg";
        new SimpleQrcodeGenerator().setRemoteLogo(logoUrl).generate(url).toFile("F:\\qrcode\\test4.png");
    }

    /**
     * 自定义配置，底色等
     */
    public static void selfConfigToFile() throws IOException {
        QrcodeConfig qrcodeConfig = new QrcodeConfig().setBorderSize(2).setPadding(10).setMasterColor("#00BFFF").setLogoBorderColor("#B0C4DE");
        String url = "http://git.oschina.net/xiaotian.yi/qrcgen";
        new SimpleQrcodeGenerator(qrcodeConfig).setLogo("F:\\qrcode\\logo.png").generate(url).toFile("F:\\qrcode\\test5.png");
    }



    public static void main(String[] args) throws Exception {
        //QrcgenCode.writeToFile();
//        QrcgenCode.writeToStream();
//        QrcgenCode.addLogoToFile();
//        QrcgenCode.addOnlineLogoToFile();
        QrcgenCode.selfConfigToFile();
    }

}
