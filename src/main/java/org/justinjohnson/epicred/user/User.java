package org.justinjohnson.epicred.user;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author johnsonj
 * @version 20140711 johnsonj
 */
public class User {
	private int    id;
	private Date   createdTime;
	private Date   lastLoginTime;
	private Date   lastModifiedTime;
	private String name;
	private String email;

	public User() {

	}

	public User(final int id, final Date createdTime, final Date lastLoginTime, final Date lastModifiedTime, final String name, final String email) {
		this.id = id;
		this.createdTime = createdTime;
		this.lastLoginTime = lastLoginTime;
		this.lastModifiedTime = lastModifiedTime;
		this.name = name;
		this.email = email;
	}

	public User(final User user) {
	    this(user.getId(), user.getCreatedTime(), user.getLastLoginTime(), user.getLastModifiedTime(), user.getName(), user.getEmail());
    }

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(final Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(final Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(final Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}

		// Null or wrong class type
		if (!(obj instanceof User)) {
			return false;
		}

		final User other = (User)obj;

		// @formatter:off
		return new EqualsBuilder()
    		.append(id, other.id)
    		.append(createdTime, other.createdTime)
    		.append(lastLoginTime, other.lastLoginTime)
    		.append(lastModifiedTime, other.lastModifiedTime)
    		.append(name, other.name)
    		.append(email, other.email)
    		.isEquals();
		// @formatter:on
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", createdTime=" + createdTime + ", lastLoginTime=" + lastLoginTime + "]";
	}
}
