package com.my.basic;

/**
 * Created by sunjinwei on 2018/11/1.
 *
 * @author sunjinwei
 * 说明何时使用内部类
 */
public class TestInnerClass {

    /**
     * 测试方法
     */
    public void run(boolean execJmqSend) {
        //判断是否允许推送
        PassCheck passCheck = new PassCheck(execJmqSend).invoke();
        execJmqSend = passCheck.isExecJmqSend();

        if (execJmqSend) {
            System.out.println("execJmqSend: " + execJmqSend);
            System.out.println("templateUrl: " + passCheck.getTemplateUrl());
        }
    }

    /**
     * 是否通过参数校验，并返回模板url
     */
    private class PassCheck {
        private boolean execJmqSend;
        private String templateUrl;

        public PassCheck(boolean execJmqSend) {
            this.execJmqSend = execJmqSend;
        }

        public boolean isExecJmqSend() {
            return execJmqSend;
        }

        public String getTemplateUrl() {
            return templateUrl;
        }

        public PassCheck invoke() {
            templateUrl = "http://www.baidu.com";
            if (templateUrl != null) {
                execJmqSend = true;
            }
            return this;
        }
    }


    public static void main(String[] args) {
        TestInnerClass testInnerClass = new TestInnerClass();
        testInnerClass.run(false);
    }
}
