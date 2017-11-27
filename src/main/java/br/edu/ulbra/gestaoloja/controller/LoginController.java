package br.edu.ulbra.gestaoloja.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoloja.input.UserInput;
import br.edu.ulbra.gestaoloja.model.User;
import br.edu.ulbra.gestaoloja.service.interfaces.SecurityService;
import br.edu.ulbra.gestaoloja.service.interfaces.UserService;

@Controller
public class LoginController {

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView index() {
    	this.userService.createUser(new User());
    	
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    @PostMapping("/login")
    public ModelAndView doLogin(UserInput userInput) {
        ModelAndView mv = new ModelAndView();
        if (userInput.getUsername() == null || userInput.getPassword() == null){
            mv.addObject("loginError", true);
            mv.setViewName("index");
        } else {
            securityService.autologin(userInput.getUsername(), userInput.getPassword());
            mv.setViewName("redirect:/user/list");
        }
        return mv;
    }
}
