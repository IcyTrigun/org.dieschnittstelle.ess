package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductCRUDStateless implements ProductCRUDRemote {

    @PersistenceContext(unitName = "erp_PU")
    private EntityManager entityManager;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        entityManager.persist(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
       return entityManager.createQuery("SELECT e FROM AbstractProduct e").getResultList();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        return entityManager.merge(update);
    }

    @Override
    public AbstractProduct readProduct(long productID) {
        return entityManager.find(AbstractProduct.class,productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        entityManager.remove(entityManager.find(AbstractProduct.class, productID));
        return true;
    }
}
