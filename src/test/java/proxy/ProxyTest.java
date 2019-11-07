package proxy;

import basic.proxy.*;
import org.junit.Before;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created 2017/6/7
 * @author yangjie
 */
public class ProxyTest {

    @Before
    public void init() {
        //设置生成jdk动态代理的代理类
        System.out.println("初始化配置...");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //设置将cglib生成的代理类字节码生成到指定位置
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/yangjie/Downloads/cglib");
    }

    @Test
    public void testJDKProxy() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        TravelProxyJDK proxy=new TravelProxyJDK();
        Object object=proxy.blind(new TravelImpl());
        Travel travel=(Travel)object;

        travel.riding();
    }

    @Test
    public void testProxyFile() {
        byte[] classFileByte = ProxyGenerator.generateProxyClass("$Proxy1", new Class[]{Travel.class}, 1);
        try {
            FileOutputStream outFile = new FileOutputStream("/Users/yangjie/Downloads/$Proxy1.class");
            outFile.write(classFileByte);
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCglibProxy() throws IOException {
        //cglib可以不依赖接口,直接根据类实体生成代理类
        TravelProxyCglib proxyCglib=new TravelProxyCglib();
        TravelImpl2 travel=(TravelImpl2) proxyCglib.getInstance(new TravelImpl2());

        travel.riding();

//        System.in.read();
    }

}
