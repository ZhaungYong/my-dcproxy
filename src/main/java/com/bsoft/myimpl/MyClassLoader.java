package com.bsoft.myimpl;

import java.io.*;

/**
 * @Auther: zhuangyong
 * @Date: 2019/5/11 20:23
 * @Description:
 */
public class MyClassLoader extends ClassLoader {
    private String baseDir;

    public MyClassLoader() {
        this.baseDir = MyClassLoader.class.getResource("").getPath();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (baseDir != null) {
            String className = MyClassLoader.class.getPackage().getName() + "." + name;
            String classpath = baseDir + name + ".class";
            File classFile = new File(classpath);
            if (classFile.exists()) {
                InputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    classFile.delete();
                }
            }
        }
        return null;
    }
}
