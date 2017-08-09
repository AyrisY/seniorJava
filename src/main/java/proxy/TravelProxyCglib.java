package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yangjie on 2017/6/7.
 */
public class TravelProxyCglib implements MethodInterceptor{

    private Object target;

    public Object getInstance(Object target){
        this.target=target;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result=null;
        System.out.println("cglib proxy begin");
        result=method.invoke(target,objects);
//        result=methodProxy.invokeSuper(target,objects);
        System.out.println("cglib proxy end");
        return result;
    }
}
