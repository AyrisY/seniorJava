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

    /**
     * 测试连接本地redis服务器
     * */
    @Test
    public void testConnect(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        //jedis ping 成功返回PONG，失败抛出异常，java.net.ConnectException: Connection refused
        try {
            System.out.println(jedis.ping());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //已连接返回true,拒绝返回false
        System.out.println(jedis.isConnected());
    }

    /**
     * 创建redis连接池测试，自定义池参数
     * */
    @Test
    public void testRedisPool(){
        System.out.println("------------------JedisPoolConfig test");

        JedisPoolConfig jpc=new JedisPoolConfig();
        //默认最大空闲连接8
        System.out.println("getMaxIdle:"+jpc.getMaxIdle());
        //默认最大连接数8
        System.out.println("getMaxTotal:"+jpc.getMaxTotal());
        //默认超时时间-1，表示不超时
        System.out.println("getMaxWaitMillis:"+jpc.getMaxWaitMillis());

        System.out.println("getBlockWhenExhausted:"+jpc.getBlockWhenExhausted());
        System.out.println("getEvictionPolicyClassName:"+jpc.getEvictionPolicyClassName());
        System.out.println("getJmxEnabled:"+jpc.getJmxEnabled());
        System.out.println("getJmxNameBase:"+jpc.getJmxNameBase());
        System.out.println("getJmxNamePrefix:"+jpc.getJmxNamePrefix());
        System.out.println("getFairness:"+jpc.getFairness());
        System.out.println("getLifo:"+jpc.getLifo());
        System.out.println("getMinEvictableIdleTimeMillis:"+jpc.getMinEvictableIdleTimeMillis());
        System.out.println("getSoftMinEvictableIdleTimeMillis:"+jpc.getSoftMinEvictableIdleTimeMillis());
        System.out.println("getTimeBetweenEvictionRunsMillis:"+jpc.getTimeBetweenEvictionRunsMillis());

        System.out.println("getNumTestsPerEvictionRun:"+jpc.getNumTestsPerEvictionRun());
        System.out.println("getTestOnBorrow:"+jpc.getTestOnBorrow());
        System.out.println("getTestOnCreate:"+jpc.getTestOnCreate());
        System.out.println("getTestOnReturn:"+jpc.getTestOnReturn());
        System.out.println("getTestWhileIdle:"+jpc.getTestWhileIdle());


        jpc.setMaxIdle(10);
        jpc.setMaxTotal(30);
        jpc.setMaxWaitMillis(2000);

        System.out.println("----------------JedisPool test-----------------------");


        JedisPool jp=new JedisPool(jpc,"127.0.0.1",6379);
        System.out.println("getMaxBorrowWaitTimeMillis:"+jp.getMaxBorrowWaitTimeMillis());
        System.out.println("getMeanBorrowWaitTimeMillis:"+jp.getMeanBorrowWaitTimeMillis());
        System.out.println("getNumActive:"+jp.getNumActive());
        System.out.println("getNumIdle:"+jp.getNumIdle());
        System.out.println("getNumWaiters:"+jp.getNumWaiters());

        Jedis jedis=jp.getResource();
        System.out.println("getNumActive:"+jp.getNumActive());

        jedis.set("foo","redis—pool");
        System.out.println(jedis.get("foo"));
        jp.returnResource(jedis);
    }

    /**
     * 测试redis的基本操作
     * */
    @Test
    public void testRedisBasic(){
        Jedis jedis=new Jedis("127.0.0.1",6379);

        System.out.println("--------------------set test----------");
        //set key
        String result=jedis.set("foo","123");
        System.out.println("jedis set result:"+result);
        System.out.println("jedis new set result:"+jedis.set("foo","321"));
        //set if not exist
        System.out.println("jedis setnx result:"+jedis.setnx("foo","321"));
        System.out.println("jedis setnx result:"+jedis.setnx("foo2","foo2"));
        System.out.println("jedis setnx expire result:"+jedis.setex("foo3",5,"foo3"));
        System.out.println("foo:\t"+jedis.get("foo"));
        System.out.println("foo2:\t"+jedis.get("foo2"));
        System.out.println("foo3:\t"+jedis.get("foo3"));
        System.out.println("foo3 expire in "+jedis.ttl("foo3")+" seconds");


        System.out.println("--------------------append and del test----------");
        //连接key的值
        jedis.append("foo","456");
        System.out.println("after foo append:foo="+jedis.get("foo"));
        //删除key
        jedis.del("foo");
        System.out.println("after foo del:foo="+jedis.get("foo"));

        System.out.println("--------------------mset test----------");
        //同时放入多个key-value
        System.out.println("mset result:"+jedis.mset("name","jyris","age","123"));
        System.out.println("mset reset result:"+jedis.mset("name","jyris","age","123"));
        System.out.println("msetnx result"+jedis.msetnx("name","jyris","age","123"));

        System.out.println("name:\t"+jedis.get("name"));
        System.out.println("age:\t"+jedis.get("age"));
    }


    /**
     * redis对map数据类型的操作
     * */
    @Test
    public void testMap(){
        Jedis jedis=new Jedis("127.0.0.1",6379);

        HashMap map=new HashMap<String,String>();
        map.put("name","jyris");
        map.put("age","123");
        map.put("sex","man");

        //hmset 直接把map放入redis
        jedis.hmset("user01",map);

        //hset  单条信息记录到map中
        jedis.hset("user02","name","tom");
        jedis.hset("user02","age","5");
        jedis.hset("user02","sex","cat");

        //获取map的value
        System.out.println("---------------------获取map的value----------------------");
        System.out.println("get single map value:"+jedis.hmget("user01","name"));
        List<String> resp=jedis.hmget("user02","name","age","sex");
        System.out.println("get multi map value:"+resp);


        //打印map的所有key和value
        System.out.println("---------------------打印map的所有key和value----------------------");
        System.out.println("get all map value,user01:"+jedis.hgetAll("user01"));
        System.out.println("get all map value,user02:"+jedis.hgetAll("user02"));
        System.out.println("get all map key,user01:"+jedis.hkeys("user01"));
        System.out.println("get all map value,user01"+jedis.hvals("user01"));

        //迭代器获取所有map的key并打印
        System.out.println("---------------------迭代器获取所有map的key并打印----------------------");
        Iterator<String> iterator=jedis.hkeys("user02").iterator();
        while(iterator.hasNext()){
            String key=iterator.next();
            System.out.println("map user02 key:"+key);
        }

        System.out.println("get map length,user01:"+jedis.hlen("user01"));

        //删除map中的key
        System.out.println("---------------------删除map中的key----------------------");
        jedis.hdel("user01","sex");
        System.out.println(jedis.hexists("user01","name"));
        System.out.println(jedis.hexists("user01","sex"));
        System.out.println(jedis.hgetAll("user01"));




    }

    /**
     * redis链表结构操作,数据结构类似链表
     * */
    @Test
    public void testList(){
        Jedis jedis=new Jedis("127.0.0.1",6379);

        //删除上次测试的遗留数据
        jedis.del("week");

        System.out.println("----------------list 新增元素--------------");
        //redis中添加链表数据
        //list中允许出现重复的元素
        jedis.rpush("week","week1","week2","week3","week1");

        System.out.println("lrange 打印链表："+jedis.lrange("week",0,-1));

        //删除链表中元素,pop,del
        System.out.println("--------------list 删除元素----------------");
        System.out.println("lpop 删除元素"+jedis.lpop("week"));
        System.out.println("rpop 删除元素"+jedis.rpop("week"));
        System.out.println("lrange 打印链表："+jedis.lrange("week",0,-1));

        System.out.println("del 删除list:"+jedis.del("week"));
        System.out.println("lrange 打印链表："+jedis.lrange("week",0,-1));

        System.out.println("--------------list 查询元素----------------");
        //链表中添加元素
        jedis.lpush("week","Tuesday","Monday","Friday");

        //打印集合中的值
        System.out.println("list 获取列表长度:"+jedis.llen("week"));
        Long listlen=jedis.llen("week");
        for(int i=0;i<listlen;i++){
            System.out.println("根据index索引打印列表值：index="+i+",value="+jedis.lindex("week",i));
        }

        System.out.println("lrange 打印链表："+jedis.lrange("week",0,-1));
    }


    /**
     * set集合的操作，数据结构类似栈
     * */
    @Test
    public void testSet(){
        Jedis jedis=new Jedis("127.0.0.1",6379);

        jedis.del("user");
        jedis.spop("user",jedis.scard("user"));

        //setnx和sadd无法同时对一个key进行操作，否则会抛出异常，因为不是一种数据类型

        System.out.println("---------------------set 新增元素-------------------");
        //set集合添加元素
        //Set集合中不允许出现重复的元素,但是list中是可以的
        jedis.sadd("user","linda","tom","jack","green","green");

        //打印集合的所有值
        System.out.println("smembers 打印set集合元素"+jedis.smembers("user"));

        System.out.println("----------------------set 删除元素-------------------");
        //删除set集合中的值,srem删除指定value,返回被删除的元素
        System.out.println("srem 删除lina:"+jedis.srem("user","linda"));
        //spop删除最后一个添加的值，可以指定删除多个，返回被删除的元素
        System.out.println("spop 删除单个数据:"+jedis.spop("user"));
        System.out.println("spop 删除多个数据:"+jedis.spop("user",2));
        System.out.println("smembers 打印set集合元素"+jedis.smembers("user"));


        System.out.println("-----------------------set 查询元素--------------------");
        //scard 显示set集合长度
        System.out.println("scard 获取集合长度:"+jedis.scard("user"));

        //验证在集合中是否存在
        jedis.sadd("user","tom","jack","green");
        System.out.println("sismember 判断jack是否存在:"+jedis.sismember("user","jack"));
        System.out.println("sismember 判断linda是否存在:"+jedis.sismember("user","linda"));

        System.out.println("scard 获取集合长度:"+jedis.scard("user"));
        //随机从集合取值，指定数字返回数组，否则返回单个参数，不会删除元素
        System.out.println("srandmember 随机从集合取值:"+jedis.srandmember("user"));
        System.out.println("srandmember 随机从集合取多个值:"+jedis.srandmember("user",2));
        System.out.println("scard 获取集合长度:"+jedis.scard("user"));

        //获取元素最后一个放入集合的值，会同时删除元素，类似栈的数据结构，先进后出，后进先出
        System.out.println("spop 获取并删除元素"+jedis.spop("user"));
        System.out.println("scard 获取集合长度:"+jedis.scard("user"));

    }


    /**
     * redis对数据机构的其他常用操作
     * */
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
