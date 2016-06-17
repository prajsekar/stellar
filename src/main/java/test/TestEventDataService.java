package test;

import java.util.List;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.Event;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.data.service.EventDataService;
import org.citrya.stellar.data.service.UserDataService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestEventDataService {
	private static UserDataService userDS = new UserDataService();
	public static void main(String[] args) {
		//TestEventCreate();
		TestEventList();
	}
	
	public static User jsonToClass() {
		String json = "{\"contacts\":[{\"type\":\"email\",\"value\":\"cnolan@gmail.com\"},{\"type\":\"mobile\",\"value\":\"77777777\"}],\"firstName\":\"Chris\",\"lastName\":\"Nolan\",\"name\":\"cnolan\"}";
		Gson gson = new GsonBuilder().create();
		User user = gson.fromJson(json, User.class);
	
		System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getId());
		for (Contact contact : user.getContacts()) {
			System.out.println("Type : " + contact.getType());
			System.out.println("Value : " + contact.getValue());
			System.out.println("ContactId : " + contact.getId());			
		}
		return user;
	}
	
	public static Event jsonToEvent() {
		String json = "{\"userId\":\"4\", \"name\":\"test_test\"}";
		Gson gson = new GsonBuilder().create();
		Event event = gson.fromJson(json, Event.class);
		return event;
	}
	
	static void TestEventCreate() {
		System.out.println("-------------- Test Create-----------");
		//User user = jsonToClass();
		//user = userDS.create(user);		
		
		/*System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getUid());
		for (Contact contact : user.getContacts()) {
			System.out.println("Type : " + contact.getType());
			System.out.println("Value : " + contact.getValue());
			System.out.println("ContactId : " + contact.getId());
			System.out.println("UserId : " + contact.getUserId());
		}
		
		
		/*Event event = new Event();
		event.setName("add_to_cart");
		event.setProperties("{\"field1\":\"value\",\"field2\":\"value2\"}");
		event.setUserId(user.getId());*/
		Event event = jsonToEvent();
		EventDataService eds = new EventDataService();
		eds.create(event);
	}
	
	
	static void TestEventList() {
		List<Event> events = new EventDataService().getAll();
		for (Event event : events) {
			System.out.println(event);			 
		}
	}
}
