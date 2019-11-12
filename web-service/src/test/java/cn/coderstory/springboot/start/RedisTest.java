package cn.coderstory.springboot.start;

import cn.coderstory.springboot.start.configure.RedisDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest

public class RedisTest {
    @Resource
   private RedisDao dao;

    @Test
    public void testRedis(){
        dao.setKey("name","coderstory");
        dao.setKey("age","18");
        System.out.println(dao.getValue("name"));
        System.out.println(dao.getValue("age"));
    }
}
