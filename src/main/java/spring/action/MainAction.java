package spring.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage(value = "json-default")
public class MainAction extends ActionSupport {
	private static final long serialVersionUID = 6796521271458311291L;

	private String sno;
	private String sno_encrypted;
	private String mode;

	@Action(value = "main", results = @Result(name = "success", location = "main/main.jsp"))
	public String abc() {
		System.out.println(mode+"   "+sno_encrypted);
//		if(mode.equals("logined")) {
//		}
		return SUCCESS;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSno_encrypted() {
		return sno_encrypted;
	}

	public void setSno_encrypted(String sno_encrypted) {
		this.sno_encrypted = sno_encrypted;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	

}
