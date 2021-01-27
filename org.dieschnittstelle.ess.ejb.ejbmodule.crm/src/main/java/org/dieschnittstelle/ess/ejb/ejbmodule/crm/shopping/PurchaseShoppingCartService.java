package org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;

import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

// TODO: PAT1: this is the interface to be provided as a rest service if rest service access is used
public interface PurchaseShoppingCartService {

	@POST
	void purchaseCartAtTouchpointForCustomer(@QueryParam("shoppingCardId")long shoppingCartId, @QueryParam("touchpointId")long touchpointId, @QueryParam("customerId") long customerId) throws ShoppingException;
	
}
