package com.my.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by sunjinwei on 2018/10/18.
 *
 * @author sunjinwei
 */
@Slf4j
public class LogPrintTest {

    public static void main(String[] args) {
        log.info("### test:{}", args);
    }

}
