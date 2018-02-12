package com.bos.utils;

import com.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class Bosutils {
	//	获取session对象
	public static HttpSession getsession() {
		return ServletActionContext.getRequest().getSession();
	}

	//	获取登录用户对象
	public static User getlogin() {
		return (User) getsession().getAttribute("login");
	}
}
