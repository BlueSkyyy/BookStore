package gz.itcast.dao;

import gz.itcast.entity.Books;
import gz.itcast.entity.PageBean;

import java.util.List;

//图书管理
public interface BooksDao {
	/**
	 * 查询所有数的信息
	 * @return
	 */
	public List<Books> findAll();
	/**
	 * 添加图书
	 * @param books
	 */
	public void addBooks(Books books);
	/**
	 * 删除图书
	 * @param id
	 */
	public void deleteBooks(int id);
	/**
	 * 查询图书
	 * @param id
	 */		
	public void updateBooks(Books books);
	/**
	 * 查询图书
	 * @param id
	 * @return
	 */
	public Books findById(int id);
	/**
	 * 分页查询图书
	 * @param curPage
	 * @param pageSize
	 * @param typesId
	 * @return
	 */
	public List<Books> pageBooks(int curPage, int pageSize, int typesId );
	/**
	 * 统计图书的总记录数
	 * @return
	 */
	public int pageCount();
}
