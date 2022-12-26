package spring.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import spring.bean.Student;
import spring.service.StudentService;

@Namespace("/")
@ParentPackage(value = "json-default")
public class IndexAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 6796521271458311291L;
	@Autowired
	StudentService studentService;

	HttpServletResponse response;
	HttpServletRequest request;

	private String sno;
	private String sname;
	private String ssex;
	private String smail;
	private String sid;
	private String sbday;
	private String sno_encrypted;

	@Action(value = "redirect", results = { @Result(name = "login", location = "login/login2.jsp"),
			@Result(name = "success", location = "main/main.jsp") })
	public String abc() {
		Cookie[] cookie = request.getCookies();
		Map<String, String> cookie_map = new HashMap<>();
		for (int i = 0; i < cookie.length; i++) {
			cookie_map.put(cookie[i].getName(), cookie[i].getValue());
		}
		String username = cookie_map.get("username");
		if (username == null || username.equals("")) {
			return LOGIN;
		}
		if (studentService.queryCookie(username) != null && !(studentService.queryCookie(username).equals(""))) {
			sno = studentService.queryCookie(username);
			Student student = studentService.queryStudent(sno);
			sname=student.getSname();
			ssex=student.getSsex()==0?"女":"男";
			smail=student.getSmail();
			sbday=student.getSbday();
			sid=student.getSid();
			return SUCCESS;
		}
		return LOGIN;
	}

	public String getSno_encrypted() {
		return sno_encrypted;
	}

	public void setSno_encrypted(String sno_encrypted) {
		this.sno_encrypted = sno_encrypted;
	}


	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSbday() {
		return sbday;
	}

	public void setSbday(String sbday) {
		this.sbday = sbday;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {

		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {

		this.request = request;
	}

}
