package com.demo.ecom.controller.normal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TodoController {

	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
}
