package cn.flashpool.service;

import cn.flashpool.domain.User;

public interface UserService {
	
	User findByUsername(String username);

	void save(User user);
}
