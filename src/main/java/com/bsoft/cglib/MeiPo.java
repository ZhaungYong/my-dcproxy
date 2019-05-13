package com.bsoft.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: MeiPo
 * @Description: TODO
 * @Author: zhuangy
 * @Date: 2019-05-13 16:11
 **/
public class MeiPo implements MethodInterceptor {
    public Object getInstance(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("hello");
        methodProxy.invokeSuper(o,objects);
        System.out.println("bye");
        return null;
    }
}
