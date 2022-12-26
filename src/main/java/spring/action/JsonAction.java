package spring.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.result.StreamResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import spring.bean.Student;
import spring.service.StudentService;
import spring.tools.IdVerify;

//@InterceptorRefs({
//    @InterceptorRef("json"),
//    @InterceptorRef("defaultStack")
//})
@ParentPackage("json-default")
@Namespace("/")
public class JsonAction extends ActionSupport {

	@Autowired
	StudentService studentService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516335522937177571L;
	private Student student;
	private String sno;
	private String sid;

	public JsonAction() {
//		student.setSno("A00001");
//		student.setSname("虧基虎來ㄉㄟˇ");
//		student.setSid("A123456789");
//		student.setSmail("A00001@test.com");
//		student.setSpwd("123456");
//		student.setSbday("1997-05-15");
//		student.setSsex(0);
//		student.setActive(1);
	}

	@Action(value = "ajax", results = {  })
	public StreamResult getAccountInfo() {
		System.out.println(sid+"      "+sno);
		Student s=studentService.queryStudent(sno);
//		System.out.println(s.toString());
		boolean idVerify=false;
		if(sid.equals("")||sid==null) {
			idVerify=false;
		}else {
		idVerify = new IdVerify().idCardVerification(sid);
		}
		if(s==null&&idVerify==true) {
			return new StreamResult(new ByteArrayInputStream("success".getBytes()));
		}else if(idVerify==false) {
		
			return new StreamResult(new ByteArrayInputStream("id".getBytes()));
		}else {
			return new StreamResult(new ByteArrayInputStream("account".getBytes()));
		}
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
