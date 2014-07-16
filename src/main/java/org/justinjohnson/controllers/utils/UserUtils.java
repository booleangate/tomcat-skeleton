package org.justinjohnson.controllers.utils;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.justinjohnson.models.user.User;
import org.justinjohnson.persistence.HibernateUtils;

/**
 * @author johnsonj
 * @version 20140716 johnsonj
 */
public class UserUtils {
	public static final User create(final String name, final String email, final String plainTextPassword) {
		final User user = new User();
		final Date now = new Date();

		user.setName(name);
		user.setEmail(email);
		user.setCreatedTime(now);
		user.setLastModifiedTime(now);
		user.setLastLoginTime(now);
		updatePassword(user, plainTextPassword);

		HibernateUtils.write(user);

		return user;
	}

	public static void update(final User user) {
		user.setLastModifiedTime(new Date());
		
	    HibernateUtils.write(user);
    }

	public static User get(final int id) {
		return HibernateUtils.read(User.class, id);
	}

	public static final boolean isPasswordMatch(final User user, final String plainTextPassword) {
		return user.getPassword().equals(getPasswordHash(plainTextPassword));
	}

	public static void updatePassword(final User user, final String newPlainTextPassword) {
		user.setPassword(getPasswordHash(newPlainTextPassword));
	}

	private static final String getPasswordHash(final String plainTextPassword) {
		return DigestUtils.sha256Hex(plainTextPassword);
	}
}
