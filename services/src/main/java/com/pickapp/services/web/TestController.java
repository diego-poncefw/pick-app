package com.pickapp.services.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {

	@GetMapping
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Object test() {
		return "HELLO WORLD";
	}

}
