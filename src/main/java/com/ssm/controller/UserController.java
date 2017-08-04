package com.ssm.controller;
 
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
 
import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.pojo.Emp;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import com.ssm.test.TestMyBatis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
 
@Controller
@RequestMapping("/user")
public class UserController {
     
    @Resource
    private UserService userservice;
    
    JedisPool pool; 
    Jedis jedis; 
    
    
    
    private static Logger logger = Logger.getLogger(UserController.class);
    
    
    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request,Model model){
    	
    	 pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");  
    	    jedis = pool.getResource(); 
    	      jedis.auth("pjy19960516"); 
    	
        //User user=userservice.getUser();
    	List<Map<String, Object>> list=null;
        if(jedis.get("listtest")==null||jedis.get("listtest").equals(""))
        {
        	
        	list=userservice.test();
        	String json=JSON.toJSONString(list);
        	jedis.set("listtest", json);
       	 	System.out.println(list+"数据库取的数据");
        	
        }else {
        	 list=(List<Map<String, Object>>) JSON.parse(jedis.get("listtest"));
        	System.out.println(list+"缓存中取的数据");
    	}
    
    	
    	
    	
    	
    	
    	model.addAttribute("list",list);
    	
    	
    	
    	//logger.debug(list);
      /*  User user=userservice.getUserById(1);
        model.addAttribute("user",user);*/
        return "showUser";
    }
}