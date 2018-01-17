import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author yangjie
 * @date 2018/1/4
 * @time 上午10:54
 */
public class BasicJavaTest {

    public static void main(String[] args) {

    }

    public void AddPro(HashMap map){
        map.put("s2",222);
    }

    @Test
    public void lanchPro(){
        HashMap map=new HashMap();

        map.put("s1",111);

        AddPro(map);

        Iterator iterator=map.entrySet().iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
