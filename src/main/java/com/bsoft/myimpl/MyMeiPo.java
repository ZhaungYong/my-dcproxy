package com.bsoft.myimpl;

import com.bsoft.common.Person;

import java.lang.reflect.Method;

/**
 * @Auther: zhuangyong
 * @Date: 2019/5/11 20:18
 * @Description:
 */
public class MyMeiPo implements MyInvocationHandler {
    private Person person;
    public Object getInstance(Person person){
        this.person = person;
        Class personClazz = person.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(),personClazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("hello");
        method.invoke(person,args);
        System.out.println("bye");
        return null;
    }
}
