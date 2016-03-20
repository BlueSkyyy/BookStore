package gz.itcast.web;

import gz.itcast.entity.Admins;
import gz.itcast.exception.LoginFailureException;
import gz.itcast.service.AdminsService;
import gz.itcast.service.AdminsServiceImpl;

import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends BaseAction implements ModelDriven<Admins> {
	//接收表单参数
	private Admins admins  = new Admins();
	public Admins getModel() {
		return admins;
	}

	//创建业务层对象
	AdminsService adminsService = new AdminsServiceImpl();
	//登录方法
	public String login(){
		try {
			
			System.out.println("--------------------");
			
			//调用业务逻辑
			Admins loginAdmins = adminsService.login(admins);
			//将用户信息保存到contextMap的session当中
			sessionMap.put("loginAdmins", loginAdmins);
			//跳转到主页
			return SUCCESS;
		} catch (LoginFailureException e) {
			//将错误信息添加到值栈
			addActionError(e.getMessage());
			//跳转到错误页面
			return INPUT;
		}
		
	}
	
	
	
}
