package org.justinjohnson.controllers;

import java.util.LinkedList;
import java.util.List;

import org.justinjohnson.controllers.utils.UserUtils;
import org.justinjohnson.models.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author johnsonj
 * @version 20140715 johnsonj
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private final String PAGE_LIST = "/user/list";
	private final String PAGE_EDIT = "/user/edit";
	
	@RequestMapping("/list")
	public String list() {
		return PAGE_LIST;
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") final int id, @RequestParam(value = "error-message", required = false) final String errorMessage, final ModelMap model) {
		final boolean isNewUser = id == 0;
		final User user;
		final List<String> errors = new LinkedList<String>();

		if (isNewUser) {
			user = new User();
		}
		else {
			user = UserUtils.get(id);
		}

		if (user == null && !isNewUser) {
			errors.add("There is no user with ID " + id);
		}
		else if (errorMessage != null && !errorMessage.isEmpty()) {
			errors.add(errorMessage);
		}

		return edit(isNewUser, user, errors, model);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestParam("name") final String name, @RequestParam("email") final String email, @RequestParam("password") final String password) {
		final User user = UserUtils.create(name, email, password);

		return "redirect:" + PAGE_EDIT + "/" + user.getId();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") final int id, @RequestParam("name") final String name, @RequestParam("email") final String email,
	    @RequestParam("password") final String password, @RequestParam("passwordNew") final String newPassword) {
		final User user = UserUtils.get(id);
		final String redirect = "redirect:" + PAGE_EDIT + "/" + + id;

		// This user does not exist, send them away.
		if (user == null) {
			return redirect + "?error-message=Cannot find that user.";
		}

		// If the user did not provide a valid password, do not updated.
		if (!UserUtils.isPasswordMatch(user, password)) {
			return redirect + "?error-message=Invalid password.";
		}

		user.setName(name);
		user.setEmail(email);

		if (newPassword != null && !newPassword.isEmpty()) {
			UserUtils.updatePassword(user, newPassword);
		}

		UserUtils.update(user);

		return redirect;
	}

	private String edit(final boolean isNewUser, final User user, final List<String> errors, final ModelMap model) {
		model.addAttribute("isNewUser", isNewUser);
		model.addAttribute("user", user);
		model.addAttribute("errors", errors);

		return PAGE_EDIT;
	}
}
