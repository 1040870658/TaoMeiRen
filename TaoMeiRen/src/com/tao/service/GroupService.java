package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tao.dao.GroupDao;
import com.tao.model.Group;
import com.tao.model.User;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;

public class GroupService {
	private GroupDao groupDao = new GroupDao(new MySqlDataProcess(MysqlConnection.getInstance()));
	public void setUp(Group group){
		groupDao.add(group);
	}
	public ArrayList<Group> displayGroups(User user){
		ArrayList<Group>groups = new ArrayList<Group>();
		ResultSet resultSet = groupDao.query(user);
		try {
			while(resultSet.next()){
				Group group = new Group();
				group.setGroupID(resultSet.getInt("groupID"));
				group.setGroupName(resultSet.getString("groupName"));
				group.setMemberNum(resultSet.getInt("memberNum"));
				group.setMaster(resultSet.getString("master"));
				groups.add(group);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}
}
