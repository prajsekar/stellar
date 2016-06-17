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

import org.citrya.stellar.data.model.User;
import org.citrya.stellar.data.service.UserDataService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {
	private static UserDataService userDS = new UserDataService();	
	
	@GET	
	public List<User> getUsers() {
		return userDS.getAll(); 
	}
	
	@POST
	public User addUser(User user) {
		return userDS.create(user);
	}	
	
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId")int id) {
		return userDS.get(id);
	}
	
	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId")String id, User user) {
		user.setUid(id);
		return userDS.update(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public User deleteUser(@PathParam("userId")int id) {		
		return userDS.delete(id);
	}
	
	@Path("/{userId}/contacts")
	public ContactService getContactService(){
		return new ContactService();
	}	
}
