package org.citrya.stellar.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetail {
	@Id
	private int id;	
	private String userName;	
		
	public UserDetail() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
