package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import java.util.List;

import static org.dieschnittstelle.ess.utils.Utils.show;

@Remote(StockSystemRESTService.class)
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
        AbstractProduct prod = productCRUD.readProduct(productId);
        stockSystem.removeFromStock((IndividualisedProductItem) prod, pointOfSaleId, units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        if(pointOfSaleId > 0 ){
            return stockSystem.getProductsOnStock(pointOfSaleId);
        } else {
            return stockSystem.getAllProductsOnStock();
        }
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        return stockSystem.getAllProductsOnStock();
    }

    @Override
    public int getUnitsOnStock(long productId, long pointOfSaleId) {
         if (productId > 0 && pointOfSaleId > 0) {
            AbstractProduct prod = productCRUD.readProduct(productId);
            return stockSystem.getUnitsOnStock((IndividualisedProductItem) prod, pointOfSaleId);

        } else if (productId > 0) {
            AbstractProduct prod = productCRUD.readProduct(productId);
            return stockSystem.getTotalUnitsOnStock((IndividualisedProductItem) prod);
        } else {
            throw new BadRequestException("At least pointOfSaleId must refer to an extisting pointOfSale");

        }
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
