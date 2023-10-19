package com.wxw.java.util;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
//        System.out.println(demo4checkNullString(null));
        demo4ThrowException("test String");
        demo4ThrowException(null);
    }

    public static String demo4checkNullString(String s) {
        return Optional.ofNullable(s).orElse("this String is null");
    }

    public static void demo4ThrowException(String s) {
        Optional.ofNullable(s).orElseThrow(() -> new RuntimeException("this String is null"));
    }

}
