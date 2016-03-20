package gz.itcast.service;

import gz.itcast.entity.Books;
import gz.itcast.entity.PageBean;

import java.util.List;

public interface BooksService {
	/**
	 * 查询全部
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
	 * 更新图书
	 * @param books
	 */
	public void updateBooks(Books books);
	/**
	 * 根据id查询
	 * @return
	 */
	public Books findById(int id);
	/**
	 * 查询图书列表
	 * @param curPage
	 * @param pageSize
	 * @param typesId
	 * @return
	 */
	public PageBean<Books> pageBooks(int curPage, int pageSize, int typesId);
	
}
