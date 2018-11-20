package cn.flashpool.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.flashpool.domain.Poolfriend;
import cn.flashpool.service.PoolfriendService;



public class PoolfriendAction extends ActionSupport implements ModelDriven<Poolfriend> {

	private Poolfriend poolfriend=new Poolfriend();
	private PoolfriendService pfs;
	
	//帖子自增减
	public String noteIncreasing(Integer flag) {
		if(flag==0) {
			poolfriend.setPyNoteNum(poolfriend.getPyNoteNum()+1);
			pfs.noteIncreasing(poolfriend);
		}else if(flag==1) {
			poolfriend.setPyNoteNum(poolfriend.getPyNoteNum()-1);
			pfs.noteDecreasing(poolfriend);           
		}else {
			System.out.println("error:This is an incorrect order");
		}
		return NONE;
	}
	
	//话题数自增减
	public String topicIncreasing(Integer flag) {
		if(flag==0) {
			poolfriend.setPyTopicNum(poolfriend.getPyTopicNum()+1);
			pfs.topicIncreasing(poolfriend);
		}else if(flag==1) {
			poolfriend.setPyTopicNum(poolfriend.getPyTopicNum()-1);
			pfs.topicDecreasing(poolfriend);
		}else {
			System.out.println("error:This is an incorrect order");
		}
		return NONE;
	}
	@Override
	public Poolfriend getModel() {
		// TODO Auto-generated method stub
		return poolfriend;
	}

}
