package proxy;

import basic.proxy.Travel;
import basic.proxy.TravelImpl;
import basic.proxy.TravelProxyJDK;

/**
 * @author yangjie
 * @date 2019-11-08
 * @time 15:01
 */
public class ProxyTest2 {

    public static void main(String[] args) {
        // 由于ProxyTest中无法根据属性生成代理类文件，
        // 在静态main方式中尝试，成功生成，生成目录com/sun/proxy
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        TravelProxyJDK proxy=new TravelProxyJDK();
        Object object=proxy.blind(new TravelImpl());
        Travel travel=(Travel)object;

        travel.riding();
    }

}
