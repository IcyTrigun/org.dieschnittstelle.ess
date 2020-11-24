package org.dieschnittstelle.ess.jrs;

import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	@Context()
	private ServletContext servletContext;

	private GenericCRUDExecutor<AbstractProduct> readExecFromServletContext() {
		return (GenericCRUDExecutor<AbstractProduct>)servletContext.getAttribute("productCRUD");
	}
	@Override
	public AbstractProduct createProduct(AbstractProduct prod) {

		return (AbstractProduct) this.readExecFromServletContext().createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts() {
		return (List) this.readExecFromServletContext().readAllObjects();
	}

	@Override
	public AbstractProduct  updateProduct(long id,
										  AbstractProduct  update) {
		return this.readExecFromServletContext().updateObject(update);

	}

	@Override
	public boolean deleteProduct(long id) {
		return this.readExecFromServletContext().deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		return (AbstractProduct) this.readExecFromServletContext().readObject(id);
	}

}
