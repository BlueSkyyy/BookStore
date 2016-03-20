package gz.itcast.service;

import gz.itcast.dao.AdminsDao;
import gz.itcast.dao.AdminsDaoImpl;
import gz.itcast.entity.Admins;
import gz.itcast.exception.LoginFailureException;
import gz.itcast.util.MD5Util;

public class AdminsServiceImpl implements AdminsService {
	AdminsDao adminsDao = new AdminsDaoImpl();
	public Admins login(Admins admins) throws LoginFailureException {
		//1 先检查用户是否存在
		Admins loginAdmins = adminsDao.findByName(admins.getName());
		if(loginAdmins==null){
			//用户不存在
			throw new LoginFailureException("用户名不存在！");
		}
		
		//2 检查密码是否正确
		//先加密用户的密码再和数据库的密码进行配对
		String md5Psw = MD5Util.md5(admins.getPassword());
		if(!loginAdmins.getPassword().equals(md5Psw)){
			System.out.println(loginAdmins.getPassword());
			//密码有误
			throw new LoginFailureException("密码错误！");
		}
		return loginAdmins;
	}

}
