package com.krismorte.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Controller
public class AdminControll {
	
	@GetMapping("/adminindex")
	public ModelAndView get() {
		ModelAndView mv = new ModelAndView("admin/index");
		return mv;
	}
	
}
