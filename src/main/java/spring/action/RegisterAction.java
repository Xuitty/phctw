package spring.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.json.annotations.JSONParameter;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import spring.bean.Student;
import spring.service.StudentService;
import spring.tools.MD5Tools;

@ParentPackage("json-default")
@Namespace("/")
public class RegisterAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 3516335522937177571L;

	@Autowired
	StudentService studentService;
	HttpServletResponse response;
	HttpServletRequest request;
	MD5Tools md5 = new MD5Tools();
	private String REGISTER = "register";
	private String sno;
	private String spwd;
	private String sname;
	private String ssex;
	private String smail;
	private String sid;
	private String sbday;
	private String sno_encrypted;
	private String message;
	private String buttonAction;
	private String verify;

	@Action(value = "register", results = {
//			@Result(type = "redirectAction", params = { "actionName", "main"}, name = "success"),
			@Result(name = "error", location = "failed.jsp"),
			@Result(name = "success", location = "register/verify.jsp"),
			@Result(type = "redirectAction", params = { "actionName", "redirect" }, name = "home"),
			@Result(name = "resend", location = "register/resend.jsp") })
	public String register() {
		if (buttonAction.equals("register")) {
			Student s = new Student(sno, sname, sbday, Integer.parseInt(ssex), smail, spwd, sid, 0);
			studentService.insertStudent(s);
			studentService.writeVerify(s);
			return SUCCESS;
		}
		if (buttonAction.equals("verify")) {
			if (studentService.queryVerify(sno).equals(verify)) {
				studentService.activeAccount(sno);
				studentService.deleteVerify(sno);
				sno_encrypted = studentService.addCookie(sno);
				Cookie c = new Cookie("username", sno_encrypted);
				c.setMaxAge(3600 * 24 * 7);
				response.addCookie(c);
				return "home";
			} else {
				message = "???????????????";
				return "resend";
			}
		}
		if (buttonAction.equals("resend")) {
			Student s = new Student(sno, sname, sbday, ssex == null ? -1 : Integer.parseInt(ssex), smail, spwd, sid, 0);
			s.setSname(studentService.queryStudent(sno).getSname());
			studentService.writeVerify(s);
			return "resend";
		}
		if (buttonAction.equals("resendLogin")) {
			return "resend";
		} else {
			message = "??????";
			return ERROR;
		}
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getSno_encrypted() {
		return sno_encrypted;
	}

	public void setSno_encrypted(String sno_encrypted) {
		this.sno_encrypted = sno_encrypted;
	}

	public String getButtonAction() {
		return buttonAction;
	}

	public void setButtonAction(String buttonAction) {
		this.buttonAction = buttonAction;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
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
