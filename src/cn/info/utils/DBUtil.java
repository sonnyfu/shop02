package cn.info.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Driver;

import cn.info.model.ShowException;

public class DBUtil {
	public static Connection getConnection() throws SQLException {
		String usename="root";
		String password="root";
		String url="jdbc:mysql://localhost:3306/shop02";
		Connection conn=null;
		
			try {
				Driver driver=new org.gjt.mm.mysql.Driver();
				Properties props=new Properties();
				props.setProperty("user", usename);
				props.setProperty("password", password);
				conn=driver.connect(url, props);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		
		return conn;
	}
	public static void close(Connection conn) {
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement ps) {
		try {
			if(ps!=null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
