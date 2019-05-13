package com.bsoft;

import com.bsoft.common.ZhuangYong;
import com.bsoft.myimpl.MyMeiPo;
import org.junit.Test;

/**
 * @ClassName: TestMyDcproxy
 * @Description: TODO
 * @Author: zhuangy
 * @Date: 2019-05-13 09:42
 **/
public class TestMyDcproxy {
    @Test
    public void testMyProxy(){
        new MyMeiPo().getInstance(new ZhuangYong());
    }
}
