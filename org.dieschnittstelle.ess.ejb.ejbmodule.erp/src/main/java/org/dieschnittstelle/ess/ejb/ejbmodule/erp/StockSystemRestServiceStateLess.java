package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

import static org.dieschnittstelle.ess.utils.Utils.show;

@Stateless
public class StockSystemRestServiceStateLess implements StockSystemRESTService{
    @EJB
    private ProductCRUDRemote productCRUD;
    @EJB
    private StockSystemLocal stockSystem;

    @Override
    public void addToStock(long productId, long pointOfSaleId, int units) {
        show("ProductCRUD"+ productCRUD);
        show("stockSystem"+ stockSystem);
        IndividualisedProductItem product = (IndividualisedProductItem) productCRUD.readProduct(productId);
        stockSystem.addToStock(product,pointOfSaleId,units);
    }

    @Override
    public void removeFromStock(long productId, long pointOfSaleId, int units) {

    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        return null;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        return null;
    }

    @Override
    public int getUnitsOnStock(long productId, long pointOfSaleId) {
        return 0;
    }

    @Override
    public int getTotalUnitsOnStock(long productId) {
        return 0;
    }

    @Override
    public List<Long> getPointsOfSale(long productId) {
        IndividualisedProductItem product = (IndividualisedProductItem)productCRUD.readProduct(productId);
        return stockSystem.getPointsOfSale(product);
    }
}
