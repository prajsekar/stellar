package org.citrya.stellar.data.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {
	private Long id;
	private String type; //Can be mail or mobile
	private Long userId;
	private String value;
		
	public Contact() {
		
	}
	
	public Contact(String type, Long userId, String value) {
		super();
		this.type = type;
		this.userId = userId;
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}		
}
