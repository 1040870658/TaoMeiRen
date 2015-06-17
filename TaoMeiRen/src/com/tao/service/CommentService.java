package com.tao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.inject.New;

import com.tao.dao.CommentDao;
import com.tao.model.Commodity;
import com.tao.model.PersonalComment;
import com.tao.utils.MySqlDataProcess;
import com.tao.utils.MysqlConnection;

public class CommentService{
	private CommentDao commentDao = new CommentDao(new MySqlDataProcess(MysqlConnection.getInstance()));
	public void addPersonalComment(PersonalComment personalComment){
		commentDao.addPersonal(personalComment);
	}
	public ArrayList<PersonalComment> queryPersonalComments(Commodity commodity){
		ArrayList<PersonalComment> personalComments = new ArrayList<PersonalComment>();
		ResultSet resultSet = commentDao.queryPersonalComments(commodity.getId());
		try {
			while(resultSet.next()){
				PersonalComment personalComment = new PersonalComment();
				personalComment.setComment(resultSet.getString("personalComment"));
				personalComment.setCommodityID(resultSet.getInt("commodityID"));
				personalComment.setEmail(resultSet.getString("email"));
				personalComment.setCommentID(resultSet.getInt("personalCommentID"));
				personalComment.setPoint(resultSet.getDouble("personalPoint"));
				personalComments.add(personalComment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<PersonalComment>();
		}
		return personalComments;
	}
	public void removePersonalComment(Commodity commodity){
		commentDao.deletePersonalComment(commodity.getId());
	}
}
