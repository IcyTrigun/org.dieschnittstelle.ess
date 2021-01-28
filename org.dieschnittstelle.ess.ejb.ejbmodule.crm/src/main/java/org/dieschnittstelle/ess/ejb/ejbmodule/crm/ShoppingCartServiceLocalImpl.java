package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppingCartServiceLocalImpl implements ShoppingCartServiceLocal{

    @PersistenceContext(unitName = "crm_PU")
    private EntityManager em;

    @Override
    public ShoppingCartRemote getCartForId(long cartId) {
            return em.find(ShoppingCartStateful.class, cartId);

    }
}
