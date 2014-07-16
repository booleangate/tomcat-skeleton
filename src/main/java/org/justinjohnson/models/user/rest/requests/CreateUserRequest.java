package org.justinjohnson.models.user.rest.requests;

import org.justinjohnson.models.user.User;

/**
 * @todo
 */
public class CreateUserRequest extends User {
	@Override
	public int getId() {
		return super.getId();
	}
	
	public User toUser() {
		return (User)this;
	}
}
