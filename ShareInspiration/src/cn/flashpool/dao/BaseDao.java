package cn.flashpool.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	
	//增
	void save(T t);
	//删(通过对象删除)
	void delete(T t);
	//删(通过id删除)
	void delete(Serializable id);
	//改
	void update(T t);
	//查
	T getById(Serializable id);
	//查询符合条件的总记录数
	Integer getTotalCount(DetachedCriteria dc);
	//查
	List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
}
