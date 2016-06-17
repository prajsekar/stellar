package org.citrya.stellar.data.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String template;
	@XmlTransient	
	private String properties;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID", nullable = false)
	@XmlTransient
	private User user;
	
	@Column(name="USER_ID", updatable=false, insertable=false)	
	private int userId;	
		
	@XmlTransient	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	/*
	@Column(name="USER_ID", nullable=false)	
	private int userId; 
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/
/*
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	@XmlTransient	
	private User user;
	
	@XmlTransient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	@Transient	
	private JsonNode content;	
	
	public Event() {
		
	}		
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public JsonNode getContent() {
		return content;
	}
	
	public void setContent(JsonNode fileds) {
		this.content = fileds;
		setPropertiesFromContent();
	}
	
	private void setPropertiesFromContent() {
		ObjectMapper mapper = new ObjectMapper();
		if(this.content != null) {
			try {				
				this.properties = mapper.writeValueAsString(this.content);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void setContentFromProperties() {		
		ObjectMapper mapper = new ObjectMapper();
		try {			
			 this.content = mapper.readValue(this.properties, JsonNode.class);
		} catch (IOException e) {
			System.out.println("Error parsing from string to jsonnode");
			e.printStackTrace();
		}	
	}
	
	@XmlTransient
	public String getProperties() {
		return this.properties;
	}

	public void setProperties(String properties) {
		this.properties = properties; 
		setContentFromProperties();
	}
	
	@Override
	public String toString() {
		return("Priting Event value::: [Id="+this.getId()+",Name="+this.getName()+",properties="+this.properties+",User="+this.userId+"]");		 
		
	}
}
