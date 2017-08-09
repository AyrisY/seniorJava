package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangjie on 2017/5/16.
 */
public class TestJedis {

    @Test
    public void testConnect(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.isConnected());
    }

    @Test
    public void testRedisPool(){
        JedisPoolConfig jpc=new JedisPoolConfig();
        jpc.setMaxIdle(10);
        jpc.setMaxTotal(100);
        jpc.setMaxWaitMillis(2000);
        jpc.setTestOnBorrow(true);

        JedisPool jp=new JedisPool(jpc,"127.0.0.1",6379);
        Jedis jedis=jp.getResource();

        jedis.set("foo","redispool");
        System.out.println(jedis.get("foo"));
        jp.returnResource(jedis);
    }

    @Test
    public void testString(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("foo","123");
        System.out.println(jedis.get("foo"));
        jedis.append("foo","456");
        System.out.println(jedis.get("foo"));
        jedis.del("foo");
        System.out.println(jedis.get("foo"));

        jedis.mset("name","jyris","age","123");
        System.out.println(jedis.get("name"));
        System.out.println(jedis.get("age"));
    }

    @Test
    public void testMap(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        HashMap map=new HashMap<String,String>();
        map.put("name","jyris");
        map.put("age","123");
        map.put("sex","man");

        jedis.hmset("user01",map);

        jedis.hset("user02","name","tom");
        jedis.hset("user02","age","5");
        jedis.hset("user02","sex","cat");

        System.out.println(jedis.hmget("user01","name"));
        System.out.println(jedis.hgetAll("user01"));
        System.out.println(jedis.hgetAll("user02"));

        System.out.println(jedis.hkeys("user01"));
        System.out.println(jedis.hvals("user01"));
        Iterator<String> iter=jedis.hkeys("user02").iterator();
        while(iter.hasNext()){
            String key=iter.next();
            System.out.println("key="+key);
        }

        System.out.println("length="+jedis.hlen("user01"));
        jedis.hdel("user01","sex");
        System.out.println(jedis.hexists("user01","name"));
        System.out.println(jedis.hexists("user01","sex"));
        System.out.println(jedis.hgetAll("user01"));

        List<String> resp=jedis.hmget("user02","name","age","sex");
        System.out.println(resp);


    }

    @Test
    public void testList(){
        /**
         * redis 中List添加元素,可以重复,map类型不行
         * */
        Jedis jedis=new Jedis("127.0.0.1",6379);
        System.out.println(jedis.lrange("week",0,-1));
        jedis.del("week");
        jedis.lpush("week","Tuesday");
        jedis.lpush("week","monday");
        jedis.rpush("week","Friday");


        System.out.println(jedis.lrange("week",0,-1));
    }

    @Test
    public void testSet(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.sadd("user","green");
        jedis.sadd("user","tom");
        jedis.sadd("user","jack");
        jedis.sadd("user","linda");

        System.out.println(jedis.smembers("user"));
        jedis.srem("user","linda");
        System.out.println(jedis.smembers("user"));
        System.out.println(jedis.sismember("user","jack"));
        System.out.println(jedis.sismember("user","linda"));
        System.out.println(jedis.scard("user"));
        System.out.println(jedis.srandmember("user",2));

    }

    @Test
    public void testSort(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.del("N");

        jedis.lpush("N","9");
        jedis.lpush("N","1");
        jedis.lpush("N","3");
        jedis.lpush("N","6");

        System.out.println(jedis.lrange("N",0,-1));
        System.out.println(jedis.sort("N"));
        System.out.println(jedis.lrange("N",0,-1));
    }
}
