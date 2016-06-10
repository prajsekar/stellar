package org.citrya.stellar.data.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int id;
	private String name;
	private String fullName;
	private String firstName;
	private String LastName;	
	private Date firstAccess;
	private Date lastLogin;	
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)	
	private List<Contact> contacts = new ArrayList<Contact>();
	
	public User() {		
		super();		
	}
	
	public User(String name, String firstName, String lastName) {
		super();
		this.name = name;
		this.firstName = firstName;
		LastName = lastName;
	}
	
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}

	public void setId(int userId) {
		this.id = userId;
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
	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}
}
