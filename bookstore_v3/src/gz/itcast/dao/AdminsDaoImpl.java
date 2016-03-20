package gz.itcast.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import gz.itcast.entity.Admins;
import gz.itcast.util.C3P0Util;

public class AdminsDaoImpl implements AdminsDao {
	QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	public Admins findByName(String name) {
		
		try {
			String sql = "select * from admins where name = ?";
			return qr.query(sql, new BeanHandler(Admins.class), name);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
