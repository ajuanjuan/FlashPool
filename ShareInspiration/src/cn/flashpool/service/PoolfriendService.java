package cn.flashpool.service;

import cn.flashpool.domain.Poolfriend;

public interface PoolfriendService {

	void noteIncreasing(Poolfriend poolfriend);

	void topicIncreasing(Poolfriend poolfriend);

	void noteDecreasing(Poolfriend poolfriend);

	void topicDecreasing(Poolfriend poolfriend);
	
	
}
