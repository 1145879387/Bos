package com.bos.intersceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import java.util.Map;

//	定义一个拦截器，
public class PrivilegeInterceptor extends MethodFilterInterceptor {
	//	不管登录和注册方法
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		ActionProxy proxy = actionInvocation.getProxy();
		String actionName = proxy.getActionName();
		String namespace = proxy.getNamespace();
		System.out.println(namespace + actionName);
//		获得session和登录标识
		Map<String, Object> session = ActionContext.getContext().getSession();
//		判断标识是否存在，如果不存在重定向到登录页面
		Object user = session.get("login");
		if (user != null) {
			return actionInvocation.invoke();
		} else {
			return "toLogin";
		}
	}
}
