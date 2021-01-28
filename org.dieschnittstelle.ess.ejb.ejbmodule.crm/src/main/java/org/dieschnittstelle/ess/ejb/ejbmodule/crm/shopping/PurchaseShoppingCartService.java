package org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;

import javax.ejb.Remote;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Remote
@Path("/purchase")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface PurchaseShoppingCartService {

	@POST
	void purchaseCartAtTouchpointForCustomer(@QueryParam("shoppingCardId")long shoppingCartId, @QueryParam("touchpointId")long touchpointId, @QueryParam("customerId") long customerId) throws ShoppingException;
	
}
