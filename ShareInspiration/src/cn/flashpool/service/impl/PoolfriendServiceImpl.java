package cn.flashpool.service.impl;

import cn.flashpool.dao.PoolfriendDao;
import cn.flashpool.domain.Poolfriend;
import cn.flashpool.service.PoolfriendService;

public class PoolfriendServiceImpl implements PoolfriendService{

	private PoolfriendDao pfd;

	//帖子数自增减
	@Override
	public void noteIncreasing(Poolfriend poolfriend) {
		pfd.update(poolfriend);
	}
	@Override
	public void noteDecreasing(Poolfriend poolfriend) {
		pfd.update(poolfriend);		
	}

	//话题数自增减
	@Override
	public void topicIncreasing(Poolfriend poolfriend) {
		pfd.update(poolfriend);		
	}
	@Override
	public void topicDecreasing(Poolfriend poolfriend) {
		pfd.update(poolfriend);
	}

	
	
	
	
}
