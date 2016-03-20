package gz.itcast.service;

import gz.itcast.entity.Types;
import gz.itcast.exception.RepeateException;

import java.util.List;

public interface TypesService {
	//查询所有的图书分类
	public List<Types> findAll();
	//添加图书分类
	public void addTypes(Types types) throws RepeateException;
	//更新信息
	public void updateTypes(Types types) throws RepeateException;
	//根据id查询
	public Types findById(int id);
	//删除分类
	public void delTypes(int id);
}
