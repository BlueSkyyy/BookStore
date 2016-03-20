package gz.itcast.service;

import gz.itcast.entity.Admins;
import gz.itcast.exception.LoginFailureException;

//接口
public interface AdminsService {
	public Admins login(Admins admins) throws LoginFailureException;
}
