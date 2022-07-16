package br.com.homeoffice.registroescolar.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/hello-servlet")
	public String hello(HttpServletRequest request) {
		request.setAttribute("nome", "Servlet");
		return "hello";
	}
	
	@GetMapping("/hello-model")
	public String hello(Model model) {
		model.addAttribute("nome", "Model");
		return "hello";
	}
	
	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("nome", "ModelAndView");
		return mv;
	}

}
