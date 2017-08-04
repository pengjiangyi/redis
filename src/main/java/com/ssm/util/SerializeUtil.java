package com.ssm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;

import com.ssm.pojo.Emp;
import com.ssm.service.UserService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SerializeUtil {
	
	  static JedisPool pool; 
	   static Jedis jedis; 
	
	
	    @Resource
	    private static UserService userService;; 
	  
	
	
    public static void main(String [] args){
    	  pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");  
  	    jedis = pool.getResource(); 
  	       jedis.auth("pjy19960516"); 
    	
      /*  String keys = "name";
        // 删数据
        //jedis.del(keys);
        // 存数据
        jedis.set(keys, "zy");
        // 取数据
        String value = jedis.get(keys);
        System.out.println(value);
        
        *
        */
        
  	     Emp emp=new Emp();  //peson类记得实现序列化接口 Serializable
  	   emp.setDepid(1);
  	 emp.setEmpname("123");
  	       
  	     setObject("100",emp);  
  	     
  	     jedis.set("123", "123");
  	     
  	     
  	     jedis.append("123", "your");
  	     System.out.println(jedis.get("123"));
         
  	   Emp p = (Emp)getObject("100");  
         System.out.println(p.getEmpname());  
           
         //list  
         List<Emp> list = new ArrayList<Emp>();  
         
         
         Emp emp1=new Emp();  //peson类记得实现序列化接口 Serializable
    	   emp1.setDepid(3);
    	 emp1.setEmpname("123");
         
    	 
    	   Emp emp2=new Emp();  //peson类记得实现序列化接口 Serializable
    	   emp2.setDepid(2);
    	 emp2.setEmpname("123");
         
         list.add(emp1);  
         list.add(emp2);       
      System.out.println(list);
      
      setObject("list", list);
      List<Emp> list2=(List<Emp>) getObject("list");
      
      System.out.println(list2.get(0).getEmpname());
  	       
 
  	 
        
        //存对象
     /*   Emp p=new Emp();  //peson类记得实现序列化接口 Serializable
        p.setDepid(1);
        p.setEmpname("123");
        jedis.set("person".getBytes(), serialize(p));
        byte[] byt=jedis.get("person".getBytes());
        Object obj=unserizlize(byt);
        if(obj instanceof Emp){
            System.out.println(((Emp) obj).getEmpname());
        }*/
    }
    
    //序列化 
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        
        return null;
    }
    
   
    /** 
     * 关闭io流对象 
     *  
     * @param closeable 
     */  
    public static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    
    /** 
     * 设置对象 
     * @param key 
     * @param obj 
     */  
    public static void setObject(String key ,Object obj){  
     try {  
         obj = obj == null ? new Object():obj;  
         jedis.set(key.getBytes(), SerializeUtil.serialize(obj));  
     } catch (Exception e) {  
         e.printStackTrace();  
     }  
 }  
      
    /** 
     * 获取对象 
     * @param key 
     * @return Object 
     */  
 public static Object getObject(String key){  
     if(jedis == null || !jedis.exists(key)){  
         return null;  
     }  
     byte[] data = jedis.get(key.getBytes());  
     return (Object)SerializeUtil.unserizlize(data);  
 }  
   

  
    
    
}