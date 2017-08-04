package com.ssm.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dep {
    private Integer depid;

    private String depname;
    
   private List<Emp>  emps;

	public List<Emp> getEmps() {
	return emps;
}

public void setEmps(List<Emp> emps) {
	this.emps = emps;
}

	public Integer getDepid() {
        return depid;
    }
    
    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname == null ? null : depname.trim();
    }
}