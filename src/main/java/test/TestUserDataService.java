package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.data.service.UserDataService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestUserDataService {
	private static UserDataService userDS = new UserDataService();
	
	public static void testGetAll() {
		System.out.println("-------------- Test Get All-----------");
		List<User> users = userDS.getAll();
		for (User user : users) {
			System.out.println("FirstName : " + user.getFirstName());
			System.out.println("FirstName : " + user.getLastName());
			System.out.println("User id : " + user.getId());
			for (Contact contact : user.getContacts()) {
				System.out.println("Type : " + contact.getType());
				System.out.println("Value : " + contact.getValue());
			}
		}
	}
	
	public static void testGet() {
		System.out.println("-------------- Test Get-----------");
		User user = userDS.get(3);
		System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getId());
	}
	
	public static void testDelete() {
		System.out.println("-------------- Test Delete-----------");
		User user = userDS.delete(3);
		System.out.println("Deleted FirstName : " + user.getFirstName());
		System.out.println("Deleted LastName : " + user.getLastName());
		System.out.println("Deleted User id : " + user.getId());
		
		User user1 = userDS.get(3);
		if(user1 == null) {
			System.out.println("Record deleted");
		}
		else {
			System.out.println("Deleted FirstName : " + user1.getFirstName());
			System.out.println("Deleted LastName : " + user1.getLastName());
			System.out.println("Deleted User id : " + user1.getId());
		}
	}
	
	public static void testUpdate() {
		System.out.println("-------------- Test Update -----------");
		User user = userDS.get(3);
		user.setFirstName("FirstName");
		user.setLastName("LastName");
		user = userDS.update(user);
		System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getId());
	}
	
	public static User jsonToClass() {
		String json = "{\"contacts\":[{\"type\":\"email\",\"value\":\"cnolan@gmail.com\"},{\"type\":\"mobile\",\"value\":\"77777777\"}],\"firstName\":\"Chris\",\"lastName\":\"Nolan\",\"name\":\"cnolan\",\"uid\":\"cnolan\"}";
		Gson gson = new GsonBuilder().create();
		User user = gson.fromJson(json, User.class);
		user.setUid(user.getUid()+new Date().toString());		
	
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
	
	public static void testCreate() {		
		System.out.println("-------------- Test Create-----------");
		User user = jsonToClass();
		user = userDS.create(user);		
		
		System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getId());
		for (Contact contact : user.getContacts()) {
			System.out.println("Type : " + contact.getType());
			System.out.println("Value : " + contact.getValue());
			System.out.println("ContactId : " + contact.getId());
//			System.out.println("UserId : " + contact.getUserId());
		}
		
		

        
        Marshaller marshaller;
		try {
			JAXBContext jc = JAXBContext.newInstance(User.class);
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		     marshaller.marshal(user, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	}
	
	public static void TestGetUserById() {
		User user = userDS.getById("cnolan1");
		System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getId());
	}
	
	public static void testValidateUser() {
		User dbUser = userDS.get(1);
		User user = userDS.validateUser(dbUser);
		System.out.println("FirstName : " + user.getFirstName());
		System.out.println("FirstName : " + user.getLastName());
		System.out.println("User id : " + user.getId());
		
		User newUser = new User();
		newUser.setUid("kBacon1");
		newUser.setFirstName("Kevin");
		newUser = userDS.validateUser(newUser);
		System.out.println("FirstName : " + newUser.getFirstName());
		System.out.println("FirstName : " + newUser.getLastName());
		System.out.println("User id : " + newUser.getId());		
	}
	public static void main(String[] args) {
		//testGetAll();
		//testGet();
		//testDelete();
		//testUpdate();
		//testCreate();
		//jsonToClass();
		//TestGetUserById();
		testValidateUser();
	}
}
