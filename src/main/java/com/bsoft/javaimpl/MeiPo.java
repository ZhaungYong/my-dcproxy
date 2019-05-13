package com.bsoft.javaimpl;

import com.bsoft.common.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: zhuangyong
 * @Date: 2019/5/12 08:31
 * @Description:
 */
public class MeiPo implements InvocationHandler {
    private Person person;

    public Object getInstance(Person person) {
        this.person = person;
        Class clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----");
        method.invoke(person,null);
        System.out.println("-----");
        return null;
    }
}
