package com.itplh;

import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class TestAllJavaLogImpl {

    private static Logger julLogger = Logger.getLogger("jul");

    private static org.apache.log4j.Logger log4jLogger = org.apache.log4j.Logger.getLogger("log4j");

    private static org.apache.logging.log4j.Logger log4j2Logger = org.apache.logging.log4j.LogManager.getLogger("log4j2");

    private static org.slf4j.Logger logbackLogger = LoggerFactory.getLogger("logback");

    public static void main(String[] args) {
        julLogger.info("jul打印日志，${java:os}");
        julLogger.warning("jul打印日志，${java:os}");

        log4jLogger.info("log4j打印日志，${java:os}");
        log4jLogger.warn("log4j打印日志，${java:os}");

        log4j2Logger.info("log4j2打印日志，{}", "${java:os}");
        log4j2Logger.warn("log4j2打印日志，{}", "${java:os}");

        logbackLogger.info("logback打印日志，{}", "${java:os}");
        logbackLogger.warn("logback打印日志，{}", "${java:os}");
    }

}
