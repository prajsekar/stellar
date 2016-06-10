package org.citrya.stellar.data;

import java.util.HashMap;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;

public class DatabaseClass {
	private static HashMap<Integer, Contact> contactTable = new HashMap<>();
	private static HashMap<Integer, User>  userTable = new HashMap<>();
	
	public static HashMap<Integer, Contact> getContacts() {
		return contactTable;
	}
	
	public static HashMap<Integer, User> getUsers() {
		return userTable;
	}
}
 