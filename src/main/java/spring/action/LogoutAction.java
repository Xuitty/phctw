package spring.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage("json-default")
public class LogoutAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {
	private static final long serialVersionUID = -5288035857586132118L;
	HttpServletResponse response;
	HttpServletRequest request;
	@Action(value = "logout",results = {@Result(name = "success",location = "login/login2.jsp")})
	public String logout() {
		Cookie c = new Cookie("username", "");
		c.setMaxAge(0);
		response.addCookie(c);
		return SUCCESS;
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	
}
