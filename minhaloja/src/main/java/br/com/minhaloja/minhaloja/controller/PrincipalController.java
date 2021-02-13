package br.com.minhaloja.minhaloja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
	@GetMapping(value = "/")
	public ModelAndView getPaginaHome() {
		return new ModelAndView("home");
	}

}
