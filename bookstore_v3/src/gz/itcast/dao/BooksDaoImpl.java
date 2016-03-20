package gz.itcast.dao;

import gz.itcast.entity.Books;
import gz.itcast.entity.PageBean;
import gz.itcast.entity.Types;
import gz.itcast.util.C3P0Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class BooksDaoImpl implements BooksDao {
	QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		
	public void addBooks(Books books) {
		try {
			String sql = "insert into books(name,covers,author,publisher,publishtime,price,rebate,authorbrief,contentbrief,t_id)" +
					" value(?,?,?,?,?,?,?,?,?,?)";
			qr.update(sql,
					books.getName(),
					books.getCovers(),
					books.getAuthor(),
					books.getPublisher(),
					books.getPublishtime(),
					books.getPrice(),
					books.getRebate(),
					books.getAuthorbrief(),
					books.getContentbrief(),
					//分类ID, 图书必须封装一个包含Id的Types对象
					books.getTypes().getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Books> findAll() {
		try {
			String sql = "SELECT b.*,t.id AS typesId,t.name AS typesName"
							+" FROM books b,TYPES t"
								+" WHERE b.t_id=t.id";
			
			return qr.query(sql, new MyBookListHandler());
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	//删除图书
	public void deleteBooks(int id) {
		try {
			String sql = "delete from books where id = ?";
			qr.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public Books findById(int id) {
		try {
			String sql = "SELECT b.*,t.id AS typesId,t.name AS typesName"
							+" FROM books b,TYPES t"
								+" WHERE b.t_id=t.id and b.id=?";
			Books book = qr.query(sql, new MyBookHandler(),id);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Books> pageBooks(int curPage, int pageSize, int typesId) {
		try {
			//如果typesId为0，就默认查询全部
			String sql = "";
			if(typesId==0){
				sql = "select b.*,t.id AS typesId,t.name AS typesName "
								+"from books b, types t "
								+"where b.t_id=t.id limit ?,?";
				return qr.query(sql, new MyBookListHandler(), (curPage-1)*pageSize,pageSize);
			}else{
				//如果typesId不为0，就默认查询指定的图书
				sql = "select b.*,t.id AS typesId,t.name AS typesName "
								+"from books b, types t "
								+"where b.t_id=t.id and t.id=? limit ?,?";
				return qr.query(sql, new MyBookListHandler(), typesId,(curPage-1)*pageSize,pageSize);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public int pageCount() {
		try {
			//查询所有的图书数目
			String sql = "select count(*) from books";
			Long count = (Long)qr.query(sql, new ScalarHandler());
			return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void updateBooks(Books books) {
		try {//"update employee set name=?,gender=?,dept=?,email=?,salary=? where id=?", 
			String sql = "update books set name=?,covers=?,author=?,publisher=?,publishtime=?,price=?,rebate=?,authorbrief=?,contentbrief=?,t_id=? where name = ?";
			qr.update(sql,
					books.getName(),
					books.getCovers(),
					books.getAuthor(),
					books.getPublisher(),
					books.getPublishtime(),
					books.getPrice(),
					books.getRebate(),
					books.getAuthorbrief(),
					books.getContentbrief(),
					//分类ID, 图书必须封装一个包含Id的Types对象
					books.getTypes().getId(),
					books.getName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}

// 封装一个图书,返回一个对象
class MyBookHandler implements ResultSetHandler{

	public Object handle(ResultSet rs) throws SQLException {
		Books book = null;
		if(rs.next()){
			book = new Books();
			
			//封装数据
			book.setId(rs.getInt("id"));
			book.setName(rs.getString("name"));
			book.setCovers(rs.getString("covers"));
			book.setAuthor(rs.getString("author"));
			book.setPublisher(rs.getString("publisher"));
			book.setPublishtime(rs.getDate("publishtime"));
			book.setPrice(rs.getDouble("price"));
			book.setRebate(rs.getDouble("rebate"));
			book.setAuthorbrief(rs.getString("authorbrief"));
			book.setContentbrief(rs.getString("contentbrief"));
			//封装type对象
			book.setTypes(new Types(rs.getInt("typesId"),rs.getString("typesName")));
	
		}
		
		return book;
	}
}
//封装多个图书，返回一个集合
class MyBookListHandler implements ResultSetHandler{

	public Object handle(ResultSet rs) throws SQLException {
		List<Books> list = new ArrayList<Books>();
		while(rs.next()){
			Books books = new Books();
			
			//封装数据
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setCovers(rs.getString("covers"));
			books.setAuthor(rs.getString("author"));
			books.setPublisher(rs.getString("publisher"));
			books.setPublishtime(rs.getDate("publishtime"));
			books.setPrice(rs.getDouble("price"));
			books.setRebate(rs.getDouble("rebate"));
			books.setAuthorbrief(rs.getString("authorbrief"));
			books.setContentbrief(rs.getString("contentbrief"));
			//封装type对象
			books.setTypes(new Types(rs.getInt("typesId"),rs.getString("typesName")));
			
			list.add(books);
		}
		
		return list;
	}
	
 }

