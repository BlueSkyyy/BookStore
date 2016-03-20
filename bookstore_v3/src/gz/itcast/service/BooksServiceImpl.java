package gz.itcast.service;

import gz.itcast.dao.AdminsDao;
import gz.itcast.dao.AdminsDaoImpl;
import gz.itcast.dao.BooksDao;
import gz.itcast.dao.BooksDaoImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.PageBean;

import java.util.List;

public class BooksServiceImpl implements BooksService {
	BooksDao booksDao = new BooksDaoImpl();
	//查询全部
	public List<Books> findAll() {
		return booksDao.findAll();
	}
	//添加图书
	public void addBooks(Books books) {
		booksDao.addBooks(books);
	}
	//删除图书
	public void deleteBooks(int id) {
		booksDao.deleteBooks(id);
	}
	//查询图书
	public Books findById(int id) {
		return booksDao.findById(id);
	}
	//查询图书的列表
	public PageBean<Books> pageBooks(int curPage, int pageSize, int typesId) {
		PageBean<Books> pageBean = new PageBean<Books>();
		//当前页信息
		pageBean.setData(booksDao.pageBooks(curPage, pageSize, typesId));
		//总记录数
		pageBean.setTotalCount(booksDao.pageCount());
		//设置当前页
		pageBean.setCurPage(curPage);
		//设置每页显示数目
		pageBean.setPageSize(pageSize);
		//返回pagebean对象
		return pageBean;
	}
	//更新图书信息
	public void updateBooks(Books books) {
		booksDao.updateBooks(books);
	}
	
}
