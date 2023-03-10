package spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.bean.Student;
import spring.service.StudentService;

@Controller
public class RegisterController {

	@Autowired
	StudentService studentservice;

	@RequestMapping("/register")
	public ModelAndView action(@RequestParam(value = "buttonAction", required = false,defaultValue = "") String action,
			@RequestParam(value = "sno", required = false) String sno,
			@RequestParam(value = "sname", required = false) String sname,
			@RequestParam(value = "spwd", required = false) String spwd,
			@RequestParam(value = "sbday", required = false) String sbday,
			@RequestParam(value = "ssex", required = false) String ssex,
			@RequestParam(value = "smail", required = false) String smail,
			@RequestParam(value = "sid", required = false) String sid,
			@RequestParam(value = "verify", required = false) String verify,
//			@RequestParam(value = "student") Student s,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in RegisterController");

		if (action.equals("register")) {
			Student s = new Student(sno, sname, sbday, Integer.parseInt(ssex), smail, spwd, sid,0);
			studentservice.insertStudent(s);
			studentservice.writeVerify(s);
			ModelAndView mv = new ModelAndView("register/verify");
			mv.addObject("sno", s.getSno());
			return mv;
		}

		else if (action.equals("verify")) {
			if (studentservice.queryVerify(sno).equals(verify)) {
				studentservice.activeAccount(sno);
				Cookie c = new Cookie("username", sno);
				c.setMaxAge(3600 * 24 * 7);
				response.addCookie(c);
				studentservice.deleteVerify(sno);
				ModelAndView mv = new ModelAndView("redirect:/redirect");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("register/resend");
				mv.addObject("message", "???????????????");
//			System.out.println(sno);

				return mv;
			}
		} else if (action.equals("resend")) {
			Student s = new Student(sno, sname, sbday, ssex==null?-1:Integer.parseInt(ssex), smail, spwd, sid,0);
			s.setSname(studentservice.queryStudent(sno).getSname());
			studentservice.writeVerify(s);
			ModelAndView mv = new ModelAndView("register/resend");
			return mv;

		}
		else if (action.equals("resendLogin")) {
			ModelAndView mv = new ModelAndView("register/resend");
			return mv;

		}
		else {
			ModelAndView mv = new ModelAndView("register/resend");
			return mv;
		}
	}

}