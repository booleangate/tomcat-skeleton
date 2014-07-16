package org.justinjohnson.models.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.justinjohnson.models.PersistableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/**
 * @author johnsonj
 * @version 20140715 johnsonj
 */
@Entity
@Table(name = "user", catalog = "tomcat_skeleton", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements PersistableModel {
	// Cache this reference to avoid the reflection lookup
	private final static String PERSISTENCE_LOCATION = User.class.getSimpleName();

	private int                 id;
	private Date                createdTime;
	private Date                lastLoginTime;
	private Date                lastModifiedTime;
	private String              name;
	private String              password;
	private String              email;

	public User() {

	}

	public User(final int id, final Date createdTime, final Date lastLoginTime, final Date lastModifiedTime, final String name, final String password, final String email) {
		this.id = id;
		this.createdTime = createdTime;
		this.lastLoginTime = lastLoginTime;
		this.lastModifiedTime = lastModifiedTime;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public User(final User user) {
		this(user.getId(), user.getCreatedTime(), user.getLastLoginTime(), user.getLastModifiedTime(), user.getName(), user.getPassword(), user.getEmail());
	}

	@Transient
	public String getPersistenceLocation() {
		return PERSISTENCE_LOCATION;
	}

	@Transient
	public Serializable getPersistenceId() {
		return id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Column(name = "createdTime", nullable = false)
	@JsonSerialize(using = DateSerializer.class)
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(final Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "lastLoginTime", nullable = false)
	@JsonSerialize(using = DateSerializer.class)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(final Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "lastModifiedTime", nullable = false)
	@JsonSerialize(using = DateSerializer.class)
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(final Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Column(name = "password", nullable = false, length = 64)
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 255)
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
    		.append(password, other.password)
    		.append(email, other.email)
    		.isEquals();
		// @formatter:on
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", createdTime=" + createdTime + ", lastLoginTime=" + lastLoginTime + ", lastModifiedTime="
		    + lastModifiedTime + "]";
	}
}
