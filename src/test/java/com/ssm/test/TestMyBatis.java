package com.ssm.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.stylesheets.LinkStyle;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Dep;
import com.ssm.pojo.Emp;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.service.impl.UserServiceImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestMyBatis {
    
     
    @Resource
    private UserService userService;;
   
    @Autowired
    private StringRedisTemplate redisTemplate;// redis操作模板
    private ValueOperations<String, Object> vOps;
    
    
    
    
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
     
    
    JedisPool pool; 
    Jedis jedis; 
    @Before 
    public void setUp() { 
    pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");  
    jedis = pool.getResource(); 
       jedis.auth("pjy19960516"); 
    } 
    
    
 
    @Test
    public void test() {
    	
    
    	
        // System.out.println(user.getUserName());
       /* User user=userService.getUserById(1);
        logger.info("值："+JSON.toJSONString(user));
        */
/*        List<Emp>  emp=userService.getEmp();
        logger.info(JSON.toJSONString(emp));
        System.out.println(emp.size());
        
        
        
        
        List<Emp> emp2=userService.selectEmpByDepId(1);
        logger.info(JSON.toJSONString(emp2));
        */
    	
    /*	List<Map<String, Object>> list=userService.test();
    	
    	System.out.println(list);*/
    	
    	
    /*	//-----添加数据---------- 
    	jedis.set("name","minxr");//向key-->name中放入了value-->minxr 
    	System.out.println(jedis.get("name"));//执行结果：minxr 
*/    	
    	

   
    if(jedis.get("json")==null||jedis.get("json").equals(""))
    {
    	List<Emp> emp4=userService.selectEmpByDepId(1);
    	String json=JSON.toJSONString(emp4);
    	jedis.set("json", json);
   	 	System.out.println(emp4+"数据库取的数据");
    	
    }else {
    	List<Emp>  emp5=JSON.parseArray(jedis.get("json"), Emp.class);
    	System.out.println(emp5+"缓存中取的数据");
	}
    
    	
    	
    
    	 			/*	redisTemplate.opsForValue().set("aliceWang","alice2");
    	             String a = redisTemplate.opsForValue().get("aliceWang");
    	             System.out.println("success2");
    	             System.out.println("a:" + a);
*/
  
    	/*List<Emp> list=userService.findByName("彭江毅");
    	System.out.println(list.size());*/
   	
    	/*List<Emp> emp=userService.requestQuery("1",null);
    	System.out.println(emp.size());*/
    	
    	/*Dep dep=userService.getDepById(1);
    	List<Emp> emps=dep.getEmps();
    	System.out.println(dep.getEmps());*/
      /* Emp emp2=userService.getEmpById(1);
       
       System.out.println(emp2.getDep().getDepid());
       
        logger.info("值："+JSON.toJSONString(emp2));*/
    }
}