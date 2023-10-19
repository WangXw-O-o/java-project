package com.wxw.java.proxy.inter.impl;

import com.wxw.java.proxy.inter.OneInterface;

public class OneInterfaceImpl2 implements OneInterface {

    @Override
    public void doSomething() {
        System.out.println("OneInterfaceImpl222222.doSomething......");
    }

    @Override
    public String returnStringMethod(String param) {
        System.out.println("OneInterfaceImpl222222.returnStringMethod......");
        return "OneInterfaceImpl222222.returnStringMethod()";
    }
}
