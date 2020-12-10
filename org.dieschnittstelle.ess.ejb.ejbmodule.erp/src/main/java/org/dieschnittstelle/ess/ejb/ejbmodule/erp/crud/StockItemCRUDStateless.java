package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(StockItemCRUDLocal.class)
public class StockItemCRUDStateless implements StockItemCRUDLocal{

    @Override
    public StockItem createStockItem(StockItem item) {
        return null;
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
        return null;
    }

    @Override
    public StockItem updateStockItem(StockItem item) {
        return null;
    }

    @Override
    public List<StockItem> readAllStockItems() {
        return null;
    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {
        return null;
    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos) {
        return null;
    }
}
