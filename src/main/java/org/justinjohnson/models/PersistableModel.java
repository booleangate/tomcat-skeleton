package org.justinjohnson.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author johnsonj
 * @version 20140715 johnsonj
 */
public interface PersistableModel {
	@JsonIgnore
	String getPersistenceLocation();

	@JsonIgnore
	Serializable getPersistenceId();
}
