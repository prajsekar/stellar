package org.citrya.stellar.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.citrya.stellar.data.model.Contact;
import org.citrya.stellar.data.service.ContactDataService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactService {	
	@GET
	public List<Contact> getContacts(@PathParam("userId") int userId) {
		return ContactDataService.Instance.getContactsForUser(userId);
	}
	
	@GET
	@Path("/{contactId}")
	public Contact getContact(@PathParam("userId") int userId, @PathParam("contactId") int id) {
		System.out.println("Contact id : " + id);
		return ContactDataService.Instance.get(id);
	}	
	
	@POST
	public List<Contact> addContact(@PathParam("userId") int userId, List<Contact> contacts){
		for (Contact contact : contacts) {
			contact.setUserId(userId);
			ContactDataService.Instance.create(contact);
		}
		return  contacts;
	}
	
	@PUT
	@Path("/{contactId}")
	public Contact updateContact(@PathParam("userId") int userId, @PathParam("contactId") int id, Contact contact){
		contact.setId(userId);
		contact.setId(id);
		return ContactDataService.Instance.update(contact);
	}
	
	@DELETE	
	public List<Contact> deleteContact(@PathParam("userId") int id) {
		List<Contact> contacts = ContactDataService.Instance.getContactsForUser(id);
		for (Contact contact : contacts) {
			ContactDataService.Instance.delete(contact.getId());
		}		
		return contacts;		
	}
	
	@DELETE
	@Path("/{contactId}")
	public Contact deleteContact(@PathParam("userId") int userId, @PathParam("contactId") int id){	
		return ContactDataService.Instance.delete(id);
	}
}
