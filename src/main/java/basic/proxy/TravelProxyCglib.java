package basic.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created 2017/6/7
 * @author yangjie
 */
public class TravelProxyCglib implements MethodInterceptor{

    private Object target;

    /**
     * cglib原理是生成被代理类的子类，这一步动态生成一个子类
     * */
    public Object getInstance(Object target){
        this.target=target;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 所有的被代理类都会在这里被拦截，实现了CallBack接口
     * */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result;
        System.out.println("cglib proxy begin");
        System.out.println("cglib proxy method:"+method.getName());
        result=method.invoke(target,args);
        methodProxy.invokeSuper(obj,args);
        System.out.println("cglib proxy end");
        return result;
    }
}
