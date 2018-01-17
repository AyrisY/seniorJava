package lock;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @author yangjie
 * @date 2018/1/15
 * @time 下午1:58
 */
public class RedisLock {

    private  Jedis jedis=new Jedis("127.0.0.1",6379);;

    public boolean setLock(String key,String value,int expire){
        Long res=jedis.setnx(key,value);
        if(res==0L){
            return false;
        }else{
            jedis.expire(key,expire);
            return true;
        }
    }

    public String getLock(String key){
        return jedis.get(key);
    }

    public boolean delLock(String key){
        String value=jedis.get(key);
        if(!StringUtils.isEmpty(value)){
            Long res=jedis.del(key);
            if(res==0L){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
