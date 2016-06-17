package test;

import java.util.List;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.data.service.ContactDataService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestContactDataService {
	private static ContactDataService dataservice = new ContactDataService();
	
	public static Object jsonToClass (String json, Class classType) {		
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, classType);
	}
	
	public static void testCreate() {
		 String jsonStr = "{\"type\":\"email\",\"value\":\"jamesb@gmail.com\",\"userId\":\"1\"}";
		 Contact contact = (Contact) jsonToClass(jsonStr, Contact.class);
		 
		 dataservice.create(contact);
		 System.out.println(contact);		 
	}
	
	public static void testDelete() {
		dataservice.delete(2);
		Contact contact = dataservice.get(2);		
		System.out.println(contact);
	}
	
	public static void testGet() {
		Contact contact = dataservice.get(2);
		System.out.println(contact);		 
	}
	
	public static void testGetAll() {
		List<Contact> contacts = dataservice.getAll();
		for (Contact contact : contacts) {
			System.out.println(contact);			 
		}
	}
	
	public static void testUpdate() {
		Contact contact = dataservice.get(2);
		contact.setValue("bondjbond");
		System.out.println(dataservice.update(contact));
	}
	
	public static void testGetContactForUser() {
		List<Contact> contacts = dataservice.getContactsForUser(4);
		for (Contact contact : contacts) {
			System.out.println(contact);			 
		}
	}
	
	public static void main(String[] args) {
		//testCreate();
		//testDelete();
		//testGet();
		//testGetAll();
		//testUpdate();
		testGetContactForUser();
	}
}
