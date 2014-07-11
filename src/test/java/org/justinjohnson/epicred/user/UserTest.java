package org.justinjohnson.epicred.user;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author johnsonj
 * @version 20140711 johnsonj
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/src/test/configuration/test.xml" })
public class UserTest {
	/**
	 * Test the constructor, getters and setters.
	 */
	@Test
	public final void basics() {
		final int id = 1;
		final Date createdTime = new Date();
		final Date lastLoginTime = new Date();
		final Date lastModifiedTime = new Date();
		final String name = "Justin";
		final String email = "justin@asdf.com";
		
		// Use empty constructor to test getters and setters
		User user = new User();
		
		user.setId(id);
		user.setCreatedTime(createdTime);
		user.setLastLoginTime(lastLoginTime);
		user.setLastModifiedTime(lastModifiedTime);
		user.setName(name);
		user.setEmail(email);
		
		assertEquals(id, user.getId());
		assertEquals(createdTime, user.getCreatedTime());
		assertEquals(lastLoginTime, user.getLastLoginTime());
		assertEquals(lastModifiedTime, user.getLastModifiedTime());
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
		
		// Test parameterized constructor
		user = new User(id, createdTime, lastLoginTime, lastModifiedTime, name, email);
		
		assertEquals(id, user.getId());
		assertEquals(createdTime, user.getCreatedTime());
		assertEquals(lastLoginTime, user.getLastLoginTime());
		assertEquals(lastModifiedTime, user.getLastModifiedTime());
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
	}
	
	/**
	 * Ensure that we have consistent hashing.
	 */
	@Test
	public final void hashing() {
		final User user1 = new User(1, null, null, null, "Justin", "justin@asdf.com");
		final User user2 = new User(2, null, null, null, "Sherelle", "sherelle@asdf.com");
		final int u1Hash = user1.hashCode();
		
		assertEquals(u1Hash, user1.hashCode());
		assertTrue(user1.hashCode() != user2.hashCode());
	}
	
	/**
	 * Test equality between self, clones, non-equal-same-type instances and different-type instances.
	 */
	@Test
	public final void equality() {
		final Date user1Date = new Date();
		final Date user2Date = new Date();
		final User user1 = new User(1, user1Date, user1Date, user1Date, "Justin", "justin@asdf.com");
		final User user1Copy = new User(user1);
		final User user2 = new User(2, user2Date, user2Date, user2Date, "Sherelle", "sherelle@asdf.com");
		
		// Identity
		assertEquals(user1, user1);
		assertEquals(user2, user2);
		
		// Identity with copy
		assertEquals(user1, user1Copy);
		
		// Non-equality
		assertFalse(user1.equals(user2));
		assertFalse(user2.equals(user1));
		
		// Null comparisons
		assertFalse(user1.equals(null));
		assertFalse(user2.equals(null));
		
		// Different types
		assertFalse(user1.equals("ohai"));
		assertFalse(user2.equals(new Integer(1)));
	}
}
