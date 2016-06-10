package org.citrya.stellar.data.service;

import java.util.Collections;
import java.util.List;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.hibernate.SessionFactoryUtil;
import org.hibernate.Session;

public class UserDataService implements DataService<User> {
	public UserDataService() {
		super();				
	}
	
	@Override
	public User create(User user) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		for (Contact contact : user.getContacts()) {
			contact.setUser(user);
			session.save(contact);
		}
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@Override
	public User delete(int id) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, id);
		for (Contact contact : user.getContacts()) {
			session.delete(contact);
		}		
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		return user;		
	}

	@Override
	public User update(User user) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		User dbUser = (User)session.get(User.class, user.getId());
		dbUser.setContacts(user.getContacts());
		dbUser.setFirstAccess(user.getFirstAccess());
		dbUser.setFirstName(user.getFirstName());
		dbUser.setFullName(user.getFullName());
		dbUser.setLastLogin(user.getLastLogin());
		dbUser.setLastName(user.getLastName());
		dbUser.setName(user.getName());
		session.beginTransaction();
		session.update(dbUser);
		session.getTransaction().commit();
		session.close();
		return dbUser;
	}

	@Override
	public List<User> getAll() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<User> users = Collections.checkedList(session.createQuery("FROM User").list(), User.class);
		session.close();
		return users;
	}

	@Override
	public User get(int id) {		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		User user = (User)session.get(User.class, id);
		session.close();
		return user;
	}
	
	public User deleteContacts(int id) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		Contact contact = (Contact)session.get(Contact.class, id);
		session.delete(contact);
		User user = (User) session.get(User.class, contact.getUserId());
		return user;
	}	
}
