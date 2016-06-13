package org.citrya.stellar.data.service;

import java.util.Collections;
import java.util.List;

import org.citrya.stellar.data.model.Event;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.hibernate.SessionFactoryUtil;
import org.hibernate.Session;

public class EventDataService implements DataService<Event>{

	@Override
	public Event create(Event event) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(event);
		session.getTransaction().commit();
		session.close();
		return event;
	}

	@Override
	public Event delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event update(Event object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getAll() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Event> users = Collections.checkedList(session.createQuery("FROM Event").list(), User.class);
		session.close();
		return users;
	}

	@Override
	public Event get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
