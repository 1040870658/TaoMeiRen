package com.tao.dao;

import java.sql.ResultSet;

import com.tao.model.User;
import com.tao.utils.DataProcess;

public class FriendDao extends Dao{
	public FriendDao(DataProcess dataProcess){
		super(dataProcess);
	}
	public ResultSet queryFriends(User user){
		String sql = "select * from relationship where host = ?";
		return dataProcess.executeQuery(sql, user.getEmail());
	}
	public ResultSet queryInvitors(User user){
		String sql = "select * from relationship where guest = ?";
		return dataProcess.executeQuery(sql, user.getEmail());
	}
	public boolean insertInvitation(User host,User guest,int type){
		String sql = "insert into relationship values(?,?,?)";
		return dataProcess.execute(sql,type,host.getEmail(),guest.getEmail());
	}
	public void updateInvitation(User host,User guest,int type){
		String sql;
		if(1 == type)
			sql = "update relationship set relation = 1 where host = ? and guest = ?";
		else
			sql = "delete from relationship where host = ? and guest = ?"; 
		dataProcess.execute(sql, host.getEmail(),guest.getEmail());
	}
}
