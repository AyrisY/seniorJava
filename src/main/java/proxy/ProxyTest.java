package proxy;

import org.junit.Test;

/**
 * Created by yangjie on 2017/6/7.
 */
public class ProxyTest {

    @Test
    public void TestJDKProxy(){
        TravelProxy proxy=new TravelProxy();
        Travel travel=(Travel)proxy.blind(new TravelImpl());

        travel.riding();
    }

    @Test
    public void TestCglib(){
        TravelProxyCglib proxyCglib=new TravelProxyCglib();
        TravelImpl2 travel=(TravelImpl2) proxyCglib.getInstance(new TravelImpl2());

        travel.riding();
    }

}
