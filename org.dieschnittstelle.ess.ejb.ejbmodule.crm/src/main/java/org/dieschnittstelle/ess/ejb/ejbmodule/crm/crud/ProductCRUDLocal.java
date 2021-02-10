package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductCRUDLocal {

    public AbstractProduct createProduct(AbstractProduct prod);


    public List<AbstractProduct> readAllProducts();


    public AbstractProduct updateProduct(AbstractProduct update);


    public AbstractProduct readProduct(long productID);


    public boolean deleteProduct( long productID);

}
