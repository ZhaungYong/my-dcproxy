package com.bsoft.javaimpl;

import com.bsoft.common.Person;
import com.bsoft.common.ZhuangYong;

/**
 * @Auther: zhuangyong
 * @Date: 2019/5/12 08:34
 * @Description:
 */
public class TestJavaDcProxy {
    public static void main(String[] args) {
        MeiPo meiPo = new MeiPo();
        Person person = (Person) meiPo.getInstance(new ZhuangYong());
        person.findLove();
    }
}
