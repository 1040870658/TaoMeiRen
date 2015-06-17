package com.tao.dao;

import java.sql.ResultSet;

import com.tao.model.PersonalComment;
import com.tao.utils.DataProcess;

public class CommentDao extends Dao{
	public CommentDao(DataProcess dataProcess){
		super(dataProcess);
	}
	public void addPersonal(PersonalComment personalComment){
		int cSize = 4;
		String sql = "insert into personal_comment(commodityID,personalComment,personalPoint,email) values(?";
		for(int i = 1;i != cSize;i ++){
			sql +=",?";
		}
		sql +=")";
		dataProcess.execute(
				sql,
				personalComment.getCommodityID(),
				personalComment.getComment(),
				personalComment.getPoint(),
				personalComment.getEmail()
				);
	}
	public ResultSet queryPersonalComments(int id) {
		String sql = "select * from personal_comment where commodityID = ?";
		return dataProcess.executeQuery(sql,id);
	}
	public void deletePersonalComment(int commodityID){
		String sql = "delete from personal_comment where commodityID = ?";
		dataProcess.execute(sql,commodityID);
	}
}
