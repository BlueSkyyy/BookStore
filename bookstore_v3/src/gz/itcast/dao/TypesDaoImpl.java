package gz.itcast.dao;

import gz.itcast.entity.Types;
import gz.itcast.util.C3P0Util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class TypesDaoImpl implements TypesDao {
	QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
	//查询全部
	public List<Types> findAll() {
		try {
			String sql = "select * from types";
			return qr.query(sql, new BeanListHandler(Types.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//添加分类
	public void addTypes(Types types) {
		try {
			String sql = "insert into types (name) values (?)";
			qr.update(sql, types.getName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//检查重名
	public boolean checkName(String name) {
		try {
			String sql = "select count(*) from types where name=?";
			Long result = (Long) qr.query(sql, new ScalarHandler(), name);
			return result>0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//更新类型
	public void updateTypes(Types types) {
		try {
			String sql = "update types set name=?  where id=?";
			qr.update(sql,types.getName(),types.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//根据id查询
	public Types findById(int id) {
		try {
			String sql = "select * from types where id = ?";
		 return qr.query(sql, new BeanHandler(Types.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	//删除分类
	public void delTypes(int id) {
		try {
			String sql = "delete from types where id = ?";
			qr.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}
