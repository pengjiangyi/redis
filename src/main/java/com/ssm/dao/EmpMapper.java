package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssm.pojo.Dep;
import com.ssm.pojo.Emp;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer empid);

    int insert(Emp record);

    int insertSelective(Emp record);
    
    List<Emp> findByName(String empname);
    
    List<Emp> requestQuery(@Param("depId") String depId,@Param("empName") String empName);
    
    List<Map<String, Object>> testSQL();
    
    List<Emp> joinQuery2();
    
    List<Emp> selectEmpByDepId(Integer depid);
    
    
    Emp selectByPrimaryKey(Integer empid);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);
}