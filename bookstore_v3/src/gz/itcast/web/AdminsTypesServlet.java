package gz.itcast.web;

import gz.itcast.entity.Types;
import gz.itcast.exception.RepeateException;
import gz.itcast.service.TypesService;
import gz.itcast.service.TypesServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminsTypesServlet
 */
public class AdminsTypesServlet extends BaseServlet {
	TypesService typesService = new TypesServiceImpl();
	//查询所有图书分类
	//url /admin/types?action=list
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询数据
		List<Types> types = typesService.findAll();
		//将数据存到域对象
		request.setAttribute("types", types);
		//通过域对象转发到显示页面
		request.getRequestDispatcher("/admin/pages/types/list.jsp").forward(request, response);
		
	}
	
	//保存分类信息
	//url /admin/types?action=save
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收参数
		String name = request.getParameter("name");
		Types types = new Types();
		types.setName(name);
		
		//2.调用业务
		try {
			if(name!=null){
				typesService.addTypes(types);
				//3.先提示添加成功，再3秒之后跳转回列表页面
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("添加成功！3秒之后跳转回列表页面！");
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/admin/types?action=list");
			}
			
		} catch (Exception e) {
			//名称重复
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/admin/pages/types/edit.jsp").forward(request, response);
		}
	}
		
	//保存分类信息
		//url /admin/types?action=update
		public void update(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			//1.接收参数
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			if(id!=null && !id.equals("")){
				Types types = new Types();
				types.setName(name);
				types.setId(Integer.parseInt(id));
		
				try {
					//2 调用业务
					typesService.updateTypes(types);
					//3 提醒修改成功
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write("修改成功！3秒之后跳转回列表页面！");
					response.setHeader("refresh", "3;url="+request.getContextPath()+"/admin/types?action=list");
			
				} catch (RepeateException e) {
					//有重名，重新修改
					request.setAttribute("msg", e.getMessage());
					request.getRequestDispatcher("/admin/pages/types/edit.jsp").forward(request, response);
				}
			}
	}
	
	//查询一个图书分类
	//URl: /admin/types?action=queryOne
	public void queryOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 获取参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		//2 调用业务，将数据转发到edit.jsp页面
		if(id!=null || !id.equals("")){
			//不为空
			Types type = new Types();
			type.setId(Integer.parseInt(id));
			type.setName(name);
			
			request.setAttribute("type", type);
			request.getRequestDispatcher("/admin/pages/types/edit.jsp").forward(request, response);
		}
		
	}
	
	//删除一个图书分类
	//URl: /admin/types?action=de
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 获取参数
		String id = request.getParameter("id");

		//2 调用业务，将数据转发到list.jsp页面
		if(id!=null || !id.equals("")){
			//不为空
			typesService.delTypes(Integer.parseInt(id));
		
			//先提示修改成功，再3秒之后跳转回列表页面
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("删除成功！3秒之后跳转回列表页面");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/admin/types?action=list");
		}
	}
}
