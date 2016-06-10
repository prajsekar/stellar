package test;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.model.User;
import org.citrya.stellar.hibernate.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestUserModel {
	public static void main(String[] args) {
		
		System.out.println("11111111111");
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		//write
//		session.beginTransaction();
//		
//		User user = new User();
//		user.setFirstName("Raja");
//		user.setLastName("sekar");
//		user.setFullName("prajsekar");
//		user.setName("prajsekar");
//		Contact contact = new Contact();
//		contact.setType("email");
//		contact.setValue("mail");
//		Contact contact2 = new Contact();
//		contact2.setType("mobile");
//		contact2.setValue("999999999");
//		session.save(user);
//		contact.setUser(user);
//		contact2.setUser(user);
//		
//		session.save(contact);
//		session.save(contact2);	
//		
//		session.getTransaction().commit();
		
		
		//read 
		System.out.println("00000000000000");
		User user1 = (User)session.get(User.class, 4);
		System.out.println("Contac size : " + user1.getContacts().size());
		session.close();		
	}
}
