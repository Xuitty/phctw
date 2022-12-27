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
import org.apache.struts2.dispatcher.Parameter.Request;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;
import org.apache.struts2.json.annotations.JSONParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import spring.bean.Student;
import spring.service.StudentService;
import spring.tools.MD5Tools;

@ParentPackage("json-default")
@Namespace("/")
public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 3516335522937177571L;

	@Autowired
	StudentService studentService;
	HttpServletResponse response;
	HttpServletRequest request;
	MD5Tools md5 = new MD5Tools();
	private final String REGISTER = "register";
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
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

	@Action(value = "login", results = {
//			@Result(type = "redirectAction", params = { "actionName", "main"}, name = "success"),
			@Result(location = "main/main.jsp", name = "success"), @Result(name = "error", location = "failed.jsp"),
			@Result(name = "register", location = "register/register.jsp"),
			@Result(name = "home", location = "login/login2.jsp"),
			@Result(name = "forgetpassword", location = "login/forgetPassword.jsp"),
			@Result(name = "resetpassword", location = "main/resetPassword.jsp") })
	public String login() {
		if (buttonAction.equals("登入")) {
			System.out.println("sno: " + sno + "spwd: " + spwd);
			if (sno == null || spwd == null || sno.equals("") || spwd.equals("")) {
				message = "欄位不可留空";
				return ERROR;
			}

			if (studentService.loginStudent(sno, spwd)) {
				sno_encrypted = studentService.addCookie(sno);
				Cookie c = new Cookie("username", sno_encrypted);
				c.setMaxAge(3600 * 24 * 7);
				response.addCookie(c);
				Student student = studentService.queryStudent(sno);
				sname = student.getSname();
				ssex = student.getSsex() == 0 ? "女" : "男";
				smail = student.getSmail();
				sbday = student.getSbday();
				sid = student.getSid();
				return SUCCESS;
			} else {
				message = "帳號密碼錯誤";
				return ERROR;
			}
		}
		if (buttonAction.equals("註冊")) {
			return REGISTER;
		}
		if (buttonAction.equals("forgetPasswordLogin")) {
			return "forgetpassword";
		}
		if (buttonAction.equals("forgetPassword")) {
			Boolean r = studentService.forgetPassword(sno, smail);
			if (r == true) {
				message = "請至信箱收信使用新密碼登入";
				return "home";
			} else {
				message = "無此帳號或信箱";
				return "forgetpassword";
			}
		} else if (buttonAction.equals("mainResetPassword")) {
			message = "請輸入舊密碼以及欲設定的新密碼";
			return "resetpassword";
		} else if (buttonAction.equals("resetPassword")) {
			System.out.println("old "+oldPassword);
			System.out.println("new "+newPassword);
			System.out.println("confirm "+confirmNewPassword);
			if (!(newPassword.equals(confirmNewPassword))) {
				message = "新密碼不一致";
				return "resetpassword";
			}
			boolean r = studentService.resetPassword(sno, oldPassword, newPassword);
			if (r) {
				message = "修改成功，請使用新密碼登入";
				Cookie c = new Cookie("username", "");
				c.setMaxAge(0);
				response.addCookie(c);
				return "home";
			} else {
				message = "舊密碼驗證錯誤";
				return "resetpassword";
			}
		}

		else {
			message = "錯誤";
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
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
