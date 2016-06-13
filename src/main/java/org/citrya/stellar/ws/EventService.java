package org.citrya.stellar.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.citrya.stellar.data.model.Event;
import org.citrya.stellar.data.service.EventDataService;


@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventService {
	EventDataService eds = new EventDataService();
	
	@POST	
	public Event Raise(Event event) {
		return eds.create(event);
	}
	
	@GET
	public List<Event> getAll() {
		return eds.getAll(); 
	}	
}
