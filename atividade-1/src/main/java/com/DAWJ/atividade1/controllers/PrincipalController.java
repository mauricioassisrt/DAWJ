package com.DAWJ.atividade1.controllers;

import javax.swing.JOptionPane;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
    public Integer contador =1;
    
    @GetMapping("contador")
    public ModelAndView recebeParam(Model model) {
        ModelAndView mv = new ModelAndView("index");
        contador++;
        System.out.println(contador);
        model.addAttribute("contador" , contador);
        mv.addObject(model);
        return mv;
    }
  
}
