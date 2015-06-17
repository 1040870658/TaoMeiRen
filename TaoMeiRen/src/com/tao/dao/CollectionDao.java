package com.tao.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.tao.model.Collection;
import com.tao.model.Commodity;
import com.tao.model.User;
import com.tao.utils.DataProcess;

public class CollectionDao extends Dao {
	public CollectionDao(DataProcess dataProcess){
		super(dataProcess);
	}
	public boolean add(Collection collection) {
		final int cSize = 6;
		String sql = "insert into collection"
				+ "(commodityID,deadLine,activeNum,collectivePrice,mailOfparticipants,currentNum)"
				+ " values(?";
		for (int i = 1; i != cSize; i++) {
			sql += ",?";
		}
		sql += ")";
		return dataProcess.execute(sql, 
				collection.getCommodity().getId(),
				collection.getDeadline(), 
				collection.getActiveNum(),
				collection.getCollectivePrice(),
				"",
				collection.getCurrentNum());
	}
	public ResultSet queryCollection(int commodityID){
		String sql = "select * from collection where commodityID = ?";
		return dataProcess.executeQuery(sql, commodityID);
	}
	public void updateOrderNum(Collection collection){
		String sql = "update collection set currentNum = ? where ID = ?";
		dataProcess.execute(sql,collection.getCurrentNum(),collection.getCollectionID());
	}
	public void copyToorder(int id){
		String sql = "insert into order_table(" +
				"commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType) select " +
				"commodityID,itemNum,mailOfbuyer,mailOfseller,deposit,dealType from collection_table " +
				"where commodityID = ?";
		dataProcess.execute(sql,id);
	}
	public void removeCollection(int id){
		String sql = "delete from collection_table where commodityID = ?";
		dataProcess.execute(sql,id);
	}
	public void deleteCollection(int commodityID){
		String sql = "delete from collection where commodityID = ?";
		dataProcess.execute(sql,commodityID);
	}
}
