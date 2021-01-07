package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TouchpointCRUDStateless implements TouchpointCRUDRemote,
		TouchpointCRUDLocal {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TouchpointCRUDStateless.class);

	@PersistenceContext(unitName = "crm_PU")
	private EntityManager em;

	/*
	 * TODO ADD1: run CreateTouchpointsAccessingCRUD in the client project with the @TransactionAttribute commented in - what happens?
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public AbstractTouchpoint createTouchpoint(AbstractTouchpoint touchpoint) throws ShoppingException {

		/*
		 * TODO ADD1: swap true/false
		 */		
		if (false/*true*/) {
			throw new RuntimeException(new ShoppingException(
					ShoppingException.ShoppingSessionExceptionReason.UNKNOWN));
		} else {
			em.persist(touchpoint);
			return touchpoint;
		}

	}

	@Override
	public AbstractTouchpoint readTouchpoint(long id) {
		AbstractTouchpoint touchpoint = em.find(AbstractTouchpoint.class, id);

		return touchpoint;
	}

	@Override
	public AbstractTouchpoint updateTouchpoint(AbstractTouchpoint touchpoint) {
		touchpoint = em.merge(touchpoint);
		return touchpoint;
	}

	@Override
	public boolean deleteTouchpoint(int id) {
		em.remove(em.find(AbstractTouchpoint.class, id));

		return true;
	}

	@Override
	public List<AbstractTouchpoint> readAllTouchpoints() {
		Query query = em.createQuery("SELECT t FROM AbstractTouchpoint AS t");

		List<AbstractTouchpoint> tps = (List<AbstractTouchpoint>) query
				.getResultList();

		return tps;
	}

}
