package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.bean.Student;
import spring.service.StudentService;

@Controller
public class HibernateController {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/hibernate")
	
	public void test() {
		
		
		String sno = "A000001";
		Student s = new Student();
		s.setSno("213123");
		s.setActive(0);
		s.setSbday("1999-09-09");
		s.setSid("A123456789");
		s.setSmail("weqwewqeq@weeqeqw");
		s.setSname("Hibernate");
		s.setSpwd("12345");
		s.setSsex(0);
		studentService.insertStudent(s);
		
	}
	
}
