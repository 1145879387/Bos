package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.User;
import com.bos.service.Iuserservice;
import com.bos.utils.Bosutils;
import com.bos.utils.MD5Util;
import com.bos.utils.crm.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
//如果是多例项目启动时则不会调用构造，访问的时候才调用构造
@Scope("prototype")
public class Useraction extends BaseAction<User> {
	private String checkcode;
	@Autowired
	private Iuserservice iuserservice;
	@Autowired
//	注意，必须要加上注解才可以注入
	private ICustomerService proxy;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String login() throws Exception {
//		List<Customer> all = proxy.findAll();
//		System.out.println(all);
//		验证码是否输入正确,从session获取生成的验证码
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
//			如果登录正确
//			User user = iuserservice.login(moudele);
//			if (user != null) {
////				登录成功
//				ServletActionContext.getRequest().getSession().setAttribute("login", user);
////				ActionContext.getContext().put("usernumber", user);
//				return "home";
//			} else {
//				this.addActionError("用户名或者密码输入错误");
//				return LOGIN;
//			}
//			获得当前用户对象，状态是未认证
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken usernamePasswordToken = new UsernamePasswordToken(moudele.getUsername(), MD5Util.MD5(moudele.getPassword()));
			try {
//				回去调用安全管理器，安全管理器回去掉ream类
				subject.login(usernamePasswordToken);
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return LOGIN;
			}
			User principal = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("login", principal);
			return "home";
		} else {
//			设置提示信息，跳转到登录界面
			this.addActionError("输入的验证码错误");
//			<S:actionerror>配合这个标签可以显示错误信息
			return LOGIN;
		}
	}


	public String logout() throws Exception {
//		session.invalidate()是销毁跟用户关联session,例如有的用户强制关闭浏览器,而跟踪用户的信息的session还存在,可是用户已经离开了。
//虽然session 生命周期浏览默认时间30分,但是在30分钟内别的用户还可以访问到前一个用户的页面,需销毁用户的session。
//session.removeAttribute()移除session中的某项属性。所以invalidate更加的专业些
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

	public String editpassword() throws IOException {
//		我需要获取到当前用户
		User getlogin = Bosutils.getlogin();
		String flag = "1";
//		不能直接更新user对象，否则hibnate会更新所有的字段，容易造成性能浪费
		//		getlogin.setPassword(getlogin.getPassword());
		try {
			iuserservice.editPassword(getlogin.getId(), moudele.getPassword());
		} catch (Exception e) {
			flag = "0";
			e.printStackTrace();
		}
//		必须要声明返回的是json形式的字符串，否则客户端认为返回的1是一个对象！则前台无法进行比较
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
//		ServletActionContext.getResponse().getWriter().print(flag);
		ServletActionContext.getResponse().getWriter().write(flag);
		//		页面发起的是ajax请求，不需要刷新页面
		return null;
	}

	public String add() throws Exception {
		iuserservice.save(moudele, rowIds);
		return "list";
	}

	public String pageQuery() throws Exception {
		iuserservice.pageQuery(pageBean);
		javatojson(pageBean, new String[]{"noticebills", "roles"});
		return null;
	}

	@Test
	public void fun1() {
//		使用test方法一定要配置注解，即@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:spring-config.xml")
//		不然会报空指针异常，然后注入属性就Ok了，如果不配置会报空指针异常，woc了
	}

	public void setRowIds(String[] rowIds) {
		this.rowIds = rowIds;
	}

	private String[] rowIds;
}
