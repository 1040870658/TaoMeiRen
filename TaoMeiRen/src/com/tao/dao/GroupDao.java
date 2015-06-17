package com.tao.dao;

import java.sql.ResultSet;

import com.tao.model.Group;
import com.tao.model.User;
import com.tao.utils.DataProcess;

public class GroupDao extends Dao{
	public GroupDao(DataProcess dataProcess){
		this.dataProcess = dataProcess;
	}
	public void add(Group group){
		String sql = "insert into group_table(groupCredit,groupName,memberNum,master)values(?,?,?,?) ";
		dataProcess.execute(sql,0,group.getGroupName(),group.getMemberNum(),group.getMaster());
	}
	public ResultSet query(User user){
		String sql = "select * from group_table where master = ?";
		 return dataProcess.executeQuery(sql, user.getEmail());
	}
}
