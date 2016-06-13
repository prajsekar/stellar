package org.citrya.stellar.data.service;

import java.awt.dnd.DnDConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.hibernate.SessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class ContactDataService implements DataService<Contact> {
	public static ContactDataService Instance = new ContactDataService();	 
	
	@Override
	public Contact create(Contact contact) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		User user = (User) session.get(User.class, contact.getUserId());
		contact.setUser(user);
		session.save(contact);
		session.getTransaction().commit();
		session.close();		
		return contact;
	}

	@Override
	public Contact delete(int id) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Contact contact = (Contact)session.get(Contact.class, id);
		session.delete(contact);
		session.getTransaction().commit();
		session.close();
		return contact;
	}

	@Override
	public Contact update(Contact contact) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Contact dbContact = (Contact)session.get(Contact.class, contact.getId());
		dbContact.setType(contact.getType());
		dbContact.setValue(contact.getValue());
		session.beginTransaction();
		session.update(dbContact);
		session.getTransaction().commit();
		session.close();
		return dbContact;
	}

	@Override
	public List<Contact> getAll() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Contact> contacts = Collections.checkedList(session.createQuery("FROM Contact").list(), Contact.class);
		session.close();
		return contacts;
	}

	@Override
	public Contact get(int id) {		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Contact contact = (Contact)session.get(Contact.class, id);
		session.close();
		return contact;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Contact> getContactsForUser(int userId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		String hql = "FROM Contact C WHERE C.userId = " + userId;
		Query query = session.createQuery(hql);
		return query.list();		
	}
}
