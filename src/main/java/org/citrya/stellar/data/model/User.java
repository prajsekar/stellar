package org.citrya.stellar.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private Long id;
	private String name;
	private String fullName;
	private String firstName;
	private String LastName;
	private Date firstAccess;
	private Date lastLogin;
	private List<Contact> contacts = new ArrayList<>();
	
	public User() {		
		super();		
	}
	
	public User(String name, String firstName, String lastName) {
		super();
		this.name = name;
		this.firstName = firstName;
		LastName = lastName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public Date getFirstAccess() {
		return firstAccess;
	}
	public void setFirstAccess(Date firstAccess) {
		this.firstAccess = firstAccess;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}
}
