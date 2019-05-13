package com.bsoft.cglib;

import com.bsoft.common.ZhuangYong;

/**
 * @ClassName: TestCglibDcproxy
 * @Description: TODO
 * @Author: zhuangy
 * @Date: 2019-05-13 16:17
 **/
public class TestCglibDcproxy {
    public static void main(String[] args) {
        ZhuangYong zhuangYong = (ZhuangYong) new MeiPo().getInstance(ZhuangYong.class);
        zhuangYong.findLove();
    }
}
