package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.bean.Student;
import spring.service.StudentService;
import spring.tools.IdVerify;


@Controller
public class AjaxController {

	@Autowired
	StudentService studentservice;
	
	@RequestMapping(value="/ajax",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String action(
			@RequestParam(value = "sno", required = false, defaultValue = "") String sno,
			@RequestParam(value = "sid", required = false, defaultValue = "") String sid,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
//		System.out.println(sno);
		Student s=studentservice.queryStudent(sno);
//		System.out.println(s.toString());
		boolean idVerify=false;
		if(sid.equals("")||sid==null) {
			idVerify=false;
		}else {
		idVerify = new IdVerify().idCardVerification(sid);
		}
		if(s==null&&idVerify==true) {
			return "成功";
		}else if(idVerify==false) {
		
		return "身分證驗證錯誤";
		}else {
			return "帳號重複";
		}
	}
}