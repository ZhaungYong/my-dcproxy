package com.bsoft.myimpl;

import com.bsoft.common.Person;
import com.bsoft.common.ZhuangYong;

/**
 * @ClassName: TestMyDcproxy
 * @Description: TODO
 * @Author: zhuangy
 * @Date: 2019-05-13 11:04
 **/
public class TestMyDcproxy {
    public static void main(String[] args) {
        Person person = (Person) new MyMeiPo().getInstance(new ZhuangYong());
        person.findLove();
    }
}
