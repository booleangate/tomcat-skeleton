package org.justinjohnson.controllers.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.justinjohnson.models.user.User;
import org.justinjohnson.models.user.rest.requests.CreateUserRequest;
import org.justinjohnson.persistence.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author johnsonj
 * @version 20140715 johnsonj
 */
@Controller
@RequestMapping("/api/user")
public class UserRestController {
	private final static Logger logger = LogManager.getLogger(UserRestController.class);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable("id") final int id) {
		if (logger.isTraceEnabled()) {
			logger.trace("Get user id=" + id);
		}
		
		final User user = new User();
		user.setId(id);
		
		return user;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody final CreateUserRequest user) {
		if (logger.isTraceEnabled()) {
			logger.trace("Create user = " + user);
		}
		
		HibernateUtils.write(user.toUser());

		return "get user " + user.getName();
	}
}
