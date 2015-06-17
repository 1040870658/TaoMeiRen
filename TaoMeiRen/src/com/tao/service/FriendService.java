package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.inject.New;

import com.tao.dao.FriendDao;
import com.tao.model.Group;
import com.tao.model.User;
import com.tao.utils.DataProcess;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;

public class FriendService {
	private FriendDao friendDao = new FriendDao(new MySqlDataProcess(MysqlConnection.getInstance()));
	private UserService userService = new UserService();
	public ArrayList<User> queryFriend(User user){
		ArrayList<User>friends = new ArrayList<User>();
		ResultSet resultSet = friendDao.queryFriends(user);
		try {
			while(resultSet.next()){
				if(resultSet.getInt("relation") == 1){
					String frienString = resultSet.getString("guest");
					User friend = userService.queryUser(frienString);
					friends.add(friend);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friends;
	}
	public ArrayList<User> queryInvitation(User user){
		ArrayList<User>invitors = new ArrayList<User>();
		ResultSet resultSet = friendDao.queryInvitors(user);
		try {
			while(resultSet.next()){
				if(resultSet.getInt("relation") == 0){
					String frienString = resultSet.getString("host");
					User friend = userService.queryUser(frienString);
					invitors.add(friend);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invitors;
	}
	public boolean invite(User host,User guest){
		return friendDao.insertInvitation(host,guest,0);
	}
	public void acceptInvitation(User host,User guest){
		friendDao.updateInvitation(guest, host,1);
		friendDao.insertInvitation(host, guest, 1);
	}
	public void rejectInvitation(User host,User guest){
		friendDao.updateInvitation(guest, host,0);
	}
}
