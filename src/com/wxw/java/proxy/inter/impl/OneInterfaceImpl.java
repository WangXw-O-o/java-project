package com.wxw.java.proxy.inter.impl;

import com.wxw.java.proxy.inter.OneInterface;

public class OneInterfaceImpl implements OneInterface {

    @Override
    public void doSomething() {
        System.out.println("OneInterfaceImpl.doSomething......");
    }

    @Override
    public String returnStringMethod(String param) {
        System.out.println("OneInterfaceImpl.returnStringMethod......");
        return "OneInterfaceImpl.returnStringMethod()";
    }
}
