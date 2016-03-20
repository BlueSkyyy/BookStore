package gz.itcast.service;

import gz.itcast.dao.TypesDao;
import gz.itcast.dao.TypesDaoImpl;
import gz.itcast.entity.Types;
import gz.itcast.exception.RepeateException;

import java.util.List;

public class TypesServiceImpl implements TypesService {
	TypesDao typesDao = new TypesDaoImpl();
	//查询所有图书分类
	public List<Types> findAll() {
		return typesDao.findAll();		
	}
	
	//添加图书分类
	public void addTypes(Types types) throws RepeateException {
		//1 判断分类名是否存在
		boolean result = typesDao.checkName(types.getName());
		if(result){
			//说明存在
			throw new RepeateException("该图书分类名已存在");
		}else{
			//保存图书分类
			typesDao.addTypes(types);
		}
	}

	public void updateTypes(Types types) throws RepeateException {
		boolean result = typesDao.checkName(types.getName());
		if(result){
			//说明存在
			throw new RepeateException("该图书分类名已存在");
		}else{
			typesDao.updateTypes(types);
		}
	
	}

	public Types findById(int id) {
		return typesDao.findById(id);
	}

	public void delTypes(int id) {
		typesDao.delTypes(id);
	}

}
