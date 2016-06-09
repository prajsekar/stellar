package org.citrya.stellar.data;

import java.util.HashMap;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;

public class DatabaseClass {
	private static HashMap<Long, Contact> contactTable = new HashMap<>();
	private static HashMap<Long, User>  userTable = new HashMap<>();
	
	public static HashMap<Long, Contact> getContacts() {
		return contactTable;
	}
	
	public static HashMap<Long, User> getUsers() {
		return userTable;
	}
}
 