package cn.flashpool.dao;

import cn.flashpool.domain.User;

public interface UserDao extends BaseDao<User>{

	User findByUsername(String username);

	void save(User user);

}
