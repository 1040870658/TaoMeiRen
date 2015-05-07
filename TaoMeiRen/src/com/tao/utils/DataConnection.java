package com.tao.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataConnection {
	public Connection connect()throws SQLException;
}
