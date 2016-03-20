package gz.itcast.web;

import gz.itcast.entity.Books;
import gz.itcast.service.BooksService;
import gz.itcast.service.BooksServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontBooksServlet
 */
public class FrontBooksServlet extends BaseServlet {
	BooksService booksService = new BooksServiceImpl();
	//显示图书详情
	//url /front/books?action=detail
	public void detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 接受id
		String id = request.getParameter("id");

		if(id!=null && !id.equals("")){
			//2 调用业务，根据id查询相关的图书
			Books book = booksService.findById(Integer.parseInt(id));
			//3 将book保存到域对象,并转发
			request.setAttribute("book", book);
			request.getRequestDispatcher("/front/detail.jsp").forward(request, response);
			
		}
		
		
	}
}
