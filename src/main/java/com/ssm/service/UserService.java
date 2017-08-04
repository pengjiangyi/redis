package com.ssm.service;
 
 
import java.util.List;
import java.util.Map;

import com.ssm.pojo.Dep;
import com.ssm.pojo.Emp;
import com.ssm.pojo.User;
 
public interface UserService {
     public User getUserById(int userId);
     
     public Emp getEmpById(int userId);
     
     public List<Emp> getEmp();
     
     public List<Emp> findByName(String empName);
     
     public List<Emp> selectEmpByDepId(int empid);
     
     public List<Map<String,Object>> test();
     
     public List<Emp> requestQuery(String depid,String empname);
     
     
     public Dep getDepById(Integer depid);
     
     
}