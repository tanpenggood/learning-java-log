package com.itplh.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestCommonsLogging {

    private static Log log = LogFactory.getLog("jcl");

    public static void main(String[] args) {
        log.info("jcl打印日志，${java:os}");
        log.warn("jcl打印日志，${java:os}");
    }

}
