package basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created 2017/6/7
 * @author yangjie
 */
public class TravelProxy implements InvocationHandler {

    private Object target;

    public Object blind(Object target){
        this.target=target;
        System.out.println("target interface:\n"+target.getClass().getInterfaces());
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println("代理开始...");
        result=method.invoke(target,args);
        System.out.println("代理结束...");
        return result;
    }
}
