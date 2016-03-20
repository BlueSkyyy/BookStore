package gz.itcast.web;

import gz.itcast.entity.Books;
import gz.itcast.entity.PageBean;
import gz.itcast.entity.Types;
import gz.itcast.service.BooksService;
import gz.itcast.service.BooksServiceImpl;
import gz.itcast.service.TypesService;
import gz.itcast.service.TypesServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	//创建service对象
	TypesService typesService = new TypesServiceImpl();
	BooksService booksService = new BooksServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 查询所有图书分类
		List<Types> types = typesService.findAll();
		
		//2 查询图书的列表
		//接受三个参数
		//接受当前页码
		String curPage = request.getParameter("curPage");
		if(curPage==null || curPage.equals("")){
			//第一次访问
			curPage = "1";
		}
		//接受每页显示的记录数
		String pageSize = request.getParameter("pageSize");
		if(pageSize==null || pageSize.equals("")){
			//第一次访问
			pageSize = "6";
		}
		//接受分类ID
		String typesId = request.getParameter("typesId");
		if(typesId==null || typesId.equals("")){
			//没有选择
			 typesId = "0";
		}
		
		//调用业务,查询数据
		PageBean<Books> pb =  booksService.pageBooks(Integer.parseInt(curPage), Integer.parseInt(pageSize), Integer.parseInt(typesId));
		
		//3 通过域对象转发到首页的index.jsp
		request.setAttribute("types", types);
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/front/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
