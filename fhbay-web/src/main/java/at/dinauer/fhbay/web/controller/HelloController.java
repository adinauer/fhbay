package at.dinauer.fhbay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping(value = "/greeting")
	public String printHelloWorld(Model model) {
		model.addAttribute("greeting", "Hello World!");

		return "hello";
	}
}
