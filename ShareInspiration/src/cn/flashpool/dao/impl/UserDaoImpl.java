package cn.flashpool.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.flashpool.dao.UserDao;
import cn.flashpool.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	//通过用户名查询用户名是否存在
	public User findByUsername(String username) {
		//Criteria		
		DetachedCriteria dc=DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		
		List<User> list=(List<User>) getHibernateTemplate().findByCriteria(dc);
		
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
}
