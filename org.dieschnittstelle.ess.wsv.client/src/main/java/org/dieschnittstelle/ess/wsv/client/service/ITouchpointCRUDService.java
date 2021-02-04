package org.dieschnittstelle.ess.wsv.client.service;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;

import javax.ws.rs.*;
import java.util.List;

@Path("/touchpoints")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface ITouchpointCRUDService {

	@GET
		//List<StationaryTouchpoint> readAllTouchpoints();
	List<AbstractTouchpoint> readAllTouchpoints();
	@GET
	@Path("/{touchpointId}")
		//StationaryTouchpoint readTouchpoint(@PathParam("touchpointId") long id);
	AbstractTouchpoint readTouchpoint(@PathParam("touchpointId") long id);
	@POST
		//StationaryTouchpoint createTouchpoint(StationaryTouchpoint touchpoint);
	AbstractTouchpoint createTouchpoint(StationaryTouchpoint touchpoint);
	@DELETE
	@Path("/{touchpointId}")
	boolean deleteTouchpoint(@PathParam("touchpointId") long id);

	@PUT
	@Path("/{touchpointId}")
		// StationaryTouchpoint updateTouchpoint(@PathParam("touchpointId") long id,StationaryTouchpoint touchpoint);
	AbstractTouchpoint updateTouchpoint(@PathParam("touchpointId") long id, StationaryTouchpoint touchpoint);
}
