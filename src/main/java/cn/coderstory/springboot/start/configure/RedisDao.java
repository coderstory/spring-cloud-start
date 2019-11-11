package cn.coderstory.springboot.start.configure;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {
    public RedisDao(StringRedisTemplate template) {
        this.template = template;
    }

   private StringRedisTemplate template;

   public void setKey(String key,String value){
        ValueOperations<String,String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);
    }
    public String getValue(String key){
        ValueOperations<String,String> ops = template.opsForValue();
        return ops.get(key);
    }
}
