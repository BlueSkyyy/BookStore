package gz.itcast.dao;

import gz.itcast.entity.Types;

import java.util.List;

public interface TypesDao {
	/**
	 * 查询所有图书分类
	 * @return
	 */
	public List<Types> findAll();
	/**
	 * 保存图书分类
	 * @param types
	 */
	public void addTypes(Types types);
	/**
	 * 检查分类名是否一样
	 * @param name
	 * @return
	 */
	public boolean checkName(String name);
	/**
	 * 更新图书分类
	 * @param types
	 */
	public void updateTypes(Types types);
	/**
	 * 根据id查询分类对象
	 * @param id
	 * @return
	 */
	public Types findById(int id);
	/**
	 * 根据ID删除对象
	 * @param id
	 */
	public void delTypes(int id);
	
	
}
