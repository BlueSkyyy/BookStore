package gz.itcast.filter;

import gz.itcast.entity.Admins;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//后台管理员的权限过滤器
public class AdminAuthFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//1.获取HttpSession
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//2.从HttpSession获取登录的标记
		HttpSession session = request.getSession();
		Admins loginAdmins = (Admins)session.getAttribute("adminsLoginInfo");
		
		//3.如果登录标记不为null，则代表已经登录成功，否则，登录失败
		if(loginAdmins!=null){
			//放行
			chain.doFilter(request, response);
		}else{
			//让其进行登录
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
