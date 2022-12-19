package spring.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import spring.bean.Student;
import spring.service.StudentService;
import spring.service.StudentServiceImpl;
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
		System.out.println(sid);
		Student s = new Student();
		s=studentservice.queryStudent(sno);
		boolean idVerify=false;
		if(sid.equals("")||sid==null) {
			idVerify=false;
		}else {
		idVerify = new IdVerify().idCardVerification(sid);
		}
		if(s.getSno()==null&&idVerify==true) {
			return "成功";
		}else if(idVerify==false) {
		
		return "身分證驗證錯誤";
		}else {
			return "帳號重複";
		}
	}
}