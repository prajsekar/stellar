package org.citrya.stellar.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.citrya.stellar.data.DatabaseClass;
import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;

public class UserDataService implements DataService<User> {
	private HashMap<Long, User> users = DatabaseClass.getUsers();
	private ContactDataService contactDS = ContactDataService.Instance;
	private Long id = 1L; 	
		
	public UserDataService() {
		super();
		User user1 = new User("jsmith", "Jason","Smith");
		user1.addContact(new Contact("email", user1.getId(), "jsmith@gmail.com"));		
		user1.addContact(new Contact("mobile", user1.getId(), "90000009"));
		
		User user2 = new User("mfox", "Megan","Fox");
		user2.addContact(new Contact("mobile", user1.getId(), "9111111111"));

		this.create(user1);
		this.create(user2);		
	}
	
	@Override
	public User create(User user) {
		user.setId(id++);
		users.put(user.getId(), user);
		for (Contact contact : user.getContacts()) {
			contact.setUserId(user.getId());
			contactDS.create(contact);
		}
		return user;
	}

	@Override
	public User delete(Long id) {
		User user = null;
		if(users.containsKey(id)) {
			user = users.get(id);
			users.remove(id);
		}
		return user;		
	}

	@Override
	public User update(User user) {
		if(users.containsKey(user.getId())) {
			users.put(user.getId(), user);
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<>(users.values());
	}

	@Override
	public User get(Long id) {		
		return users.get(id);
	}
	
	public User deleteContacts(Long id) {
		User user = users.get(id);
		if(user != null) {
			user.setContacts(null);
		}
		return user;
	}	
}
