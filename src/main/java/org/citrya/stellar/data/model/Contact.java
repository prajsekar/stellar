package org.citrya.stellar.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String type; //Can be mail or mobile	
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable = false)
	private User user;
	@Transient
	private int userId;
	private String value;
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact() {
		
	}
	
	public Contact(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public Contact(String type, int userId, String value) {
		super();
		this.type = type;		
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}		
}
