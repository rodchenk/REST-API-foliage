package com.foliage.security.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.foliage.security.common.DBConstants;

@Entity
@Table(name=DBConstants.USER_AUTH_TABLE_NAME)
public class UserAuthData {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=DBConstants.USER_ID_COLUMN_NAME) 
	private int id;
	
	@Column(name=DBConstants.USER_EMAIL_COLUMN_NAME) 
	private String email;
	
	@Column(name=DBConstants.USER_SIGNED_IN_COLUMN_NAME) 
	private Date signedIn;
	
	@Column(name=DBConstants.USER_PASSWORD_COLUMN_NAME) 
	private String password;
	
	@Column(name=DBConstants.USER_TOKEN_COLUMN_NAME) 
	private String token;
	
	@Column(name=DBConstants.USER_AUTH_COLUMN_NAME) 
	private String name;

	public String getName() {
		return name;
	}

	public UserAuthData setName(String name) {
		this.name = name;
		return this;
	}

	public int getId() {
		return id;
	}

	public UserAuthData setId(int id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserAuthData setEmail(String email) {
		this.email = email;
		return this;
	}

	public Date getLastSignIn() {
		return signedIn;
	}

	public UserAuthData setLastSignIn(Date lastSignIn) {
		this.signedIn = lastSignIn;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserAuthData setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getToken() {
		return token;
	}

	public UserAuthData setToken(String token) {
		this.token = token;
		return this;
	}

	
	
}
