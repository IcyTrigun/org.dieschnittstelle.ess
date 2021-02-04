package org.dieschnittstelle.ess.jrs;

import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/touchpoints")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface ITouchpointCRUDService {
	
	@GET
	List<StationaryTouchpoint> readAllTouchpoints();
	//List<AbstractTouchpoint> readAllTouchpoints();
	@GET
	@Path("/{touchpointId}")
	StationaryTouchpoint readTouchpoint(@PathParam("touchpointId") long id);
//AbstractTouchpoint readTouchpoint(@PathParam("touchpointId") long id);
	@POST
	StationaryTouchpoint createTouchpoint(StationaryTouchpoint touchpoint);
	// AbstractTouchpoint createTouchpoint(StationaryTouchpoint touchpoint);
	@DELETE
	@Path("/{touchpointId}")
	boolean deleteTouchpoint(@PathParam("touchpointId") long id);

	@PUT
	@Path("/{touchpointId}")
	 StationaryTouchpoint updateTouchpoint(@PathParam("touchpointId") long id,StationaryTouchpoint touchpoint);
	//AbstractTouchpoint updateTouchpoint(@PathParam("touchpointId") long id, StationaryTouchpoint touchpoint);

}
