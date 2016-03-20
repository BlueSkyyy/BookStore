package gz.itcast.web;

import gz.itcast.entity.Admins;
import gz.itcast.exception.LoginFailureException;
import gz.itcast.service.AdminsService;
import gz.itcast.service.AdminsServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminsServlet
 */
public class AdminsServlet extends BaseServlet {
	AdminsService adminsService = new AdminsServiceImpl();
	
	//登录方法
	//url /admin?action=login
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 接收参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
	
		Admins admins = new Admins();
		admins.setName(name);
		admins.setPassword(password);
		
		try {
			//2 调用业务
			Admins loginAdmins = adminsService.login(admins);
			
			//3 登陆成功
			request.getSession().setAttribute("adminsLoginInfo", loginAdmins);
			response.sendRedirect(request.getContextPath()+"/admin/pages/index.jsp");
		} catch (LoginFailureException e) {
			//4 没有登录成功，将错误信息转发到login.jsp页面上
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);	
		}

	}

	//注销方法
	//url admin?action=logout
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除登录时的标记数据
		HttpSession session = request.getSession(false);
		session.removeAttribute("adminsLoginInfo");
		
		response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		
	}
}
