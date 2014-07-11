package org.justinjohnson.skeletons.tomcat.controllers.api;

import org.justinjohnson.models.user.rest.requests.CreateUserRequest;
import org.justinjohnson.persistence.HibernateUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody final CreateUserRequest user) {
		HibernateUtils.save(user.toUser());
		
		return "get user " + user.getName();
	}
}
