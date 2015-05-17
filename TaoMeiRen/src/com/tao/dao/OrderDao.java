package com.tao.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tao.model.Order;
import com.tao.model.User;
import com.tao.utils.DataProcess;

public class OrderDao
{
	private DataProcess dataProcess;
	private User user;
	public OrderDao(DataProcess dataProcess,User user){
		this.dataProcess = dataProcess;
		this.user=user;
	}
	
	public List<Order> getOnePriceOrder()
	{
		List<Order> orders=new ArrayList<Order>();
		String sql="select * from order_table where (mailOfbuyer=? or mailOfseller=?) and dealType=?";
		String params[]={user.getEmail(),user.getEmail(),"1"};
		ResultSet resultSet=dataProcess.executeQuery(sql, params);
		if (resultSet != null)
		{
			try
			{
				while (resultSet.next())
				{
					Order order=new Order();
					order.orderID=resultSet.getInt("orderID");
					order.commodityID=resultSet.getInt("commodityID");
					order.itemNum=resultSet.getInt("itemNum");
					order.mailOfbuyer=resultSet.getString("mailOfbuyer");
					order.mailOfseller=resultSet.getString("mailOfseller");
					order.deposit=resultSet.getDouble("deposit");
					order.dealType=resultSet.getInt("dealType");
					orders.add(order);
				}
				
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return orders;
	}
	
}
