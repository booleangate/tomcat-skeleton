package org.justinjohnson.epicred.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@RequestParam(value = "name", required = false) final String name, final ModelMap model) {
		final String message;

		if (name == null || name.isEmpty()) {
			message = "Welcome to ZomboCom!";
		}
		else {
			message = "Welcome, " + name;
		}

		model.addAttribute("message", message);

		return "index";
	}
}
