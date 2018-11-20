package cn.flashpool.service.impl;

import org.springframework.transaction.annotation.Transactional;

import cn.flashpool.dao.UserDao;
import cn.flashpool.domain.User;
import cn.flashpool.service.UserService;
import cn.flashpool.utils.UUIDUtils;



@Transactional
public class UserServiceImpl implements UserService{
	
	private UserDao ud;

	//通过用户名查询用户
	public User findByUsername(String username) {
		return ud.findByUsername(username);
	}
	
	//用户注册
	@Override
	public void save(User user) {
		//激活状态
		user.setUserState(0);//0代表用户已激活 1代表用户未激活
		//生成激活码
		//保存
		ud.save(user);
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}	

	
}
