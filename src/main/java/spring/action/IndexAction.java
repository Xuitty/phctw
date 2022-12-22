package spring.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ParentPackage(value = "struts-default")
public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6796521271458311291L;

	@Action(value = "abc", results = @Result(name = "success", location = "StrutsTest.jsp"))
	public String abc() {

		return SUCCESS;
	}

}
