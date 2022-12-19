package spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

//	@Resource
//	StudentServiceImpl ssi;
	@RequestMapping("/logout")

	public ModelAndView deleteCookie(HttpServletResponse response) {
		Cookie c = new Cookie("username", "empty");
		c.setMaxAge(0);
		response.addCookie(c);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

}