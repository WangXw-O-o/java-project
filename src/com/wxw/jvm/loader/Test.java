package com.wxw.jvm.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试类加载过程
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestClassLoader loader = new TestClassLoader("/Users/xl-shuke/IdeaProjects/private/java-project/src/com/wxw/jvm/loader/TestClass.class");
        String packageName = "com.wxw.jvm.loader.TestClass";
        Class<?> aClass = loader.findClass(packageName);
        System.out.println("load by : " + aClass.getClassLoader());

        Method test = aClass.getDeclaredMethod("test");
        Object o = aClass.getDeclaredConstructor().newInstance();
        test.invoke(o);

        TestClass testClass = new TestClass();

        System.out.println(aClass.getName());
        System.out.println(testClass.getClass().getName());

        System.out.println(aClass);    // class com.wxw.jvm.loader.TestClass
        System.out.println(testClass); // com.wxw.jvm.loader.TestClass@18b4aac2

        System.out.println(o instanceof TestClass); //false
    }

}
