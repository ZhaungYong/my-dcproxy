package com.bsoft.myimpl;

import java.lang.reflect.Method;

/**
 * @Auther: zhuangyong
 * @Date: 2019/5/11 20:19
 * @Description:
 */
public interface MyInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
