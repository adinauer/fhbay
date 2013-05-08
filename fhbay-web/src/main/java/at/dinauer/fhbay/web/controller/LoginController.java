package at.dinauer.fhbay.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	 
		@RequestMapping(value="/", method = RequestMethod.GET)
		public String printWelcome(ModelMap model) {
			model.addAttribute("message", "Spring Security Custom Form example");
			
			return "index";
		}
	 
		@RequestMapping(value="/login", method = RequestMethod.GET)
		public String login(ModelMap model) {
	 
			return "index";
	 
		}
	 
		@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
		public String loginerror(ModelMap model) {
	 
			model.addAttribute("error", "true");
			return "index";
	 
		}
	 
		@RequestMapping(value="/logout", method = RequestMethod.GET)
		public String logout(ModelMap model) {
	 
			return "index";
	 
		}
}
