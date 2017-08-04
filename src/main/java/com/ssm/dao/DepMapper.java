package com.ssm.dao;

import java.util.List;

import com.ssm.pojo.Dep;

public interface DepMapper {
    int deleteByPrimaryKey(Integer depid);

    int insert(Dep record);

    int insertSelective(Dep record);

    List<Dep> findByEmp(Integer empid);
    
    
    Dep selectByPrimaryKey(Integer depid);

    int updateByPrimaryKeySelective(Dep record);

    int updateByPrimaryKey(Dep record);
}