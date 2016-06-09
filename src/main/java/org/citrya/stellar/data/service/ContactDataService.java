package org.citrya.stellar.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.citrya.stellar.data.DatabaseClass;
import org.citrya.stellar.data.model.Contact;

public class ContactDataService implements DataService<Contact> {
	public static ContactDataService Instance = new ContactDataService(); 
	private HashMap<Long, Contact> contacts = DatabaseClass.getContacts();
	private static Long id = 1L; 
	
	@Override
	public Contact create(Contact contact) {
		System.out.println("Creting contact for : " + id);
		contact.setId(id++);
		contacts.put(contact.getId(), contact);
		return contact;
	}

	@Override
	public Contact delete(Long id) {
		Contact contact = null;
		if(contacts.containsKey(id)) {
			contact = contacts.get(id);
			contacts.remove(id);
		}
		return contact;		
	}

	@Override
	public Contact update(Contact contact) {
		if(contacts.containsKey(contact.getId())) {
			contacts.put(contact.getId(), contact);
		}
		return contact;
	}

	@Override
	public List<Contact> getAll() {
		return new ArrayList<>(contacts.values());
	}

	@Override
	public Contact get(Long id) {		
		return contacts.get(id);
	}	
	
	public List<Contact> getContactsForUser(Long userId) {
		List<Contact> contactList= new ArrayList<>();
		for (Contact contact : contacts.values()) {
			if(contact.getUserId() == userId) {
				contactList.add(contact);
			}
		}		
		return contactList;
	}
}
