package cn.flashpool.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.flashpool.domain.User;
import cn.flashpool.service.UserService;
import cn.flashpool.utils.UUIDUtils;
import cn.flashpool.utils.aliYunMessageUtils;



public class UserAction extends ActionSupport implements ModelDriven<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user=new User();
	private UserService us;
	
	private String code;
	
	/**
	 * 
	 * @return
	 */
	
	public String toRegistPage() {
		return "toRegistPage";
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findByName() throws Exception {	
		// 调用Service进行查询:
		User existUser = us.findByUsername(user.getUserName());
		// 获得response对象,向页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户已经存在
			response.getWriter().println("<font color='red'>手机号已被注册</font>");
		} else {
			// 没查询到该用户:用户可以使用
			response.getWriter().println("<font color='green'>手机号未被注册可以使用</font>");
		}
		return NONE;
	}
	
	/**
	 * 获取短信码
	 * @return
	 */
	public String getMessageCode(){
		//生成验证码
		String code=UUIDUtils.getUUID();
		//发送短信验证码
		try {
			SendSmsResponse response = aliYunMessageUtils.sendSms("13140009384",code);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将验证码保存在session中
		Map<String, Object> sessionScope=ActionContext.getContext().getSession();
		sessionScope.put("code", code);
		return NONE;
	}
	/*
	 * 用户注册
	 */
	public String registing() {
		Map<String,Object> sessionScope=ActionContext.getContext().getSession();
		//对比验证码
		if(sessionScope.get("code").equals(code)) {
			System.out.println(user.toString());
			us.save(user);
			System.out.println("out");
		}else {
			// 获得response对象,向页面输出:
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().println("<font color='red'>请输入验证码</font>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "registSuccess";
	}
	/*
	 *	用户登录 
	 */
	public String toLoginPage() {
		return "toLoginPage";
	}
	/*
	 * ajax进行异步校验----登录
	 */
	public String checkLogin(String username,String password) throws Exception {	
		// 调用Service进行查询:
		User existUser = us.findByUsername(user.getUserName());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		//判断用户是否存在
		if (existUser != null) {
			//判断密码是否正确
			if(existUser.getUserPassword().equals(user.getUserPassword())) {
				//判断用户是否激活
				if(existUser.getUserState()==1) {
					return "loginSuccess";
				}else if(existUser.getUserState()==0){
					response.getWriter().println("<font color='green'>用户未激活</font>");
				}
			}else {
				response.getWriter().println("<font color='green'>密码错误</font>");
			}
		} else {
			response.getWriter().println("<font color='green'>用户不存在，请先注册</font>");
		}
		return NONE;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	public void setUs(UserService us) {
		this.us = us;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
