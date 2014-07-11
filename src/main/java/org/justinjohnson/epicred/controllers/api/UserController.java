package org.justinjohnson.epicred.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/api/user")
public class UserController {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String getUser(final String name) {
		return "get user " + name;
	}
	

}
