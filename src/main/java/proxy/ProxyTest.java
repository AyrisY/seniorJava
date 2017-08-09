package proxy;

import org.junit.Test;

/**
 * Created by yangjie on 2017/6/7.
 */
public class ProxyTest {


    @Test
    public void TestJDKProxy(){
        //接口生成的类,JDK动态代理原理
        TravelProxy proxy=new TravelProxy();
        Travel travel=(Travel)proxy.blind(new TravelImpl());

        travel.riding();
    }

    @Test
    public void TestCglib(){
        //cglib可以不依赖接口,直接根据类实体生成代理类
        TravelProxyCglib proxyCglib=new TravelProxyCglib();
        TravelImpl2 travel=(TravelImpl2) proxyCglib.getInstance(new TravelImpl2());

        travel.riding();
    }

}
