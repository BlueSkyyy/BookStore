package gz.itcast.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	static String url="jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf-8";
	static String user="root";
	static String password="root";
	static String driverClass="com.mysql.jdbc.Driver";
	
	//连接数据库
	private static DataSource ds = new ComboPooledDataSource("bookstore");

	/**
	 * 提供一个获得连接的方法
	 */
	public static Connection getConnection(){
		//Connection conn = null;
		try {
			//conn = empSysDB.getConnection();
		return 	ds.getConnection();
			
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 提供一个获取连接池的方法
	 * @return
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(conn!=null)conn.close();
			if(stmt!=null)stmt.close();
			if(rs!=null)rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
