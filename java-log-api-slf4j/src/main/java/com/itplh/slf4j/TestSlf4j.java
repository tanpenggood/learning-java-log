package com.itplh.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSlf4j {

    private static Logger logger = LoggerFactory.getLogger("slf4j");

    public static void main(String[] args) {
        logger.info("slf4j打印日志，{}", "${java:os}");
        logger.warn("slf4j打印日志，{}", "${java:os}");
    }

}
