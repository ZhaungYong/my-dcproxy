package com.bsoft.myimpl;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Auther: zhuangyong
 * @Date: 2019/5/11 20:21
 * @Description:
 */
public class MyProxy {
    private static final String ln = "\n";

    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces, MyInvocationHandler h) {
        FileWriter fw = null;
        //生成源代码
        String src = generateSrc(interfaces[0]);
        //将源码写入磁盘
        String dirName = MyProxy.class.getResource("").getPath();
        File javaFile = new File(dirName, "$Proxy0.java");
        try {
            fw = new FileWriter(javaFile);
            fw.write(src);
            fw.flush();
            //编译生成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager javaFileManager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = javaFileManager.getJavaFileObjects(javaFile);

            JavaCompiler.CompilationTask task = compiler.getTask(null, javaFileManager, null, null, null, iterable);
            task.call();
            javaFileManager.close();
            //将.class字节码文件加载到jvm中
            Class $proxy0Clazz = loader.findClass("$Proxy0");
            //删除源码文件并返回代理对象
            Constructor constructor = $proxy0Clazz.getConstructor(MyInvocationHandler.class);
            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            javaFile.delete();
        }
        return null;
    }

    public static String generateSrc(Class<?> interfaces) {
        StringBuilder src = new StringBuilder();
        String packageName = interfaces.getPackage().getName();
        src.append("package com.bsoft.myimpl;");
        src.append("import " + interfaces.getName() + ";" + ln);
        src.append("import com.bsoft.myimpl.MyInvocationHandler;" + ln);
        src.append("import java.lang.reflect.*;" + ln);
        src.append("public final class $Proxy0 implements " + interfaces.getName() + "{" + ln);
        src.append("MyInvocationHandler h;" + ln);
        src.append("public $Proxy0(MyInvocationHandler h){" + ln);
        src.append("this.h=h;" + ln + "}" + ln);
        for (Method m : interfaces.getMethods()) {
            src.append("public " + m.getReturnType().getName() + " " + m.getName() + "(){" + ln);

            src.append("try{" + ln);
            src.append("Method m = " + interfaces.getName() + ".class.getMethod(\"" +m.getName()+"\",new Class[]{});" + ln);
            src.append("this.h.invoke(this,m,null);" + ln);
            src.append("}catch(Throwable e){e.printStackTrace();}" + ln);
            src.append("}" + ln);
        }
        src.append("}");
        return src.toString();
    }


}
