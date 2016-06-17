package org.citrya.stellar.delivery;

import org.citrya.stellar.data.model.Contact;

public class DeliveryContentBean {
	private Contact contact;
	private String url;
	private String company;
	private String phoneNumberId;
	private String msgTemplate; 
	
	public DeliveryContentBean() {
		
	}
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhoneNumberId() {
		return phoneNumberId;
	}
	public void setPhoneNumberId(String phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}
	public String getMsgTemplate() {
		return msgTemplate;
	}
	public void setMsgTemplate(String msgTemplate) {
		this.msgTemplate = msgTemplate;
	}
}
