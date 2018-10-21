package com.foliage.api.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.foliage.api.common.ApiDBConstants;

@Entity
@Table(name=ApiDBConstants.USER_TABLE_NAME)
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=ApiDBConstants.USER_ID_COLUMN_NAME) 
	private int id;
	
	private String firstname;
	
	private String lastname;
	
	private Date birthday;

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getFirstname() {
		return firstname;
	}

	public User setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public User setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public Date getBirthday() {
		return birthday;
	}

	public User setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	} 
}
//(unique = true, nullable = false)