package gz.itcast.dao;

import gz.itcast.entity.Admins;

public interface AdminsDao {
	public Admins findByName(String name);
}
