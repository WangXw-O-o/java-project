package com.wxw.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class TestClassLoader extends ClassLoader {

    private final String filePath;

    public TestClassLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected Class<?> findClass(String name) {
        System.out.println("TestClassLoader findClass execute");
        byte[] classFile = getClassFile();
        if (classFile != null) {
            return defineClass(name, classFile, 0, classFile.length);
        }
        return null;
    }

    private byte[] getClassFile() {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            byte[] bytes = new byte[1024];
            int size = 0;
            while ((size = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, size);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
