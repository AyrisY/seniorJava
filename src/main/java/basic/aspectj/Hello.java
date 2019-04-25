package basic.aspectj;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjie
 * @date 2018/2/9
 * @time 上午11:21
 */
public class Hello {
    public void sayHello(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Hello hello=new Hello();
        hello.sayHello();

        Map map = new HashMap();
        map.put("customerId", "sdfa2232323");
        System.out.println(JSONObject.toJSONString(map));
    }

}
