package org.citrya.stellar.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String type; //Can be mail or mobile	
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable = false)
	@XmlTransient
	private User user;
	
	@Column(name="USER_ID", updatable=false, insertable=false)	
	private int userId;
	
	private String value;	
	
	@XmlTransient	
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
		if(user != null) {
			userId = user.getId();
		}
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Id : " + this.getId());
		buffer.append("\n Type : " + this.getType());
		buffer.append("\n Value : " + this.getValue());
		buffer.append("\n User id : " + this.getUserId());
		return buffer.toString();
	}
	/*public int getUserId() {
		return user.getId();
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/
}
