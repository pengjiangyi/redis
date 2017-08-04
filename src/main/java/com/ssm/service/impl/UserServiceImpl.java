package com.ssm.service.impl;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;

import com.ssm.dao.DepMapper;
import com.ssm.dao.EmpMapper;
import com.ssm.dao.UserMapper;
import com.ssm.pojo.Dep;
import com.ssm.pojo.Emp;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
 
 
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userDao;
    @Resource
    private EmpMapper empMapper;
    @Resource
    private DepMapper depMapper;
    
    public User getUserById(int userId) {
     return this.userDao.selectByPrimaryKey(userId);
    }
	@Override
	public List<Emp> getEmp() {
		return this.empMapper.joinQuery2();
	}
	@Override
	public Emp getEmpById(int userId) {
		
		return this.empMapper.selectByPrimaryKey(userId);
	}
	@Override
	public List<Emp> selectEmpByDepId(int depid) {
		return this.empMapper.selectEmpByDepId(depid);
		
	}
	@Override
	public List<Map<String, Object>> test() {
		return this.empMapper.testSQL();
	}
	@Override
	public List<Emp> findByName(String empName) {
		return this.empMapper.findByName(empName);
	}
	@Override
	public List<Emp> requestQuery(String depid, String empname) {
		
		return this.empMapper.requestQuery(depid, empname);
	}
	@Override
	public Dep getDepById(Integer depid) {
		return this.depMapper.selectByPrimaryKey(1);
	}
	
	
	
 
     
}