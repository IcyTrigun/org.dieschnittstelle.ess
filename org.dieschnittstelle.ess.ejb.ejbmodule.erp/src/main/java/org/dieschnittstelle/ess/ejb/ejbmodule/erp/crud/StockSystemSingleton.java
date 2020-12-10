package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemLocal;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.EJB;
import java.util.List;

public class StockSystemSingleton implements StockSystemLocal {

    @EJB
    private StockItemCRUDLocal siCrud;

    @EJB
    private PointOfSaleCRUDLocal posCrud;
    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCrud.readPointOfSale((pointOfSaleId));
       StockItem stockItem = siCrud.readStockItem(product,pos);
        if(stockItem==null){
            siCrud.createStockItem(stockItem);
        }else{
            stockItem.setUnits(stockItem.getUnits()+units);
            siCrud.updateStockItem(stockItem);
        }
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        addToStock(product,pointOfSaleId,-units);
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
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        return 0;
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        return 0;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        return null;
    }

    @Override
    public List<StockItem> getCompleteStock() {
        return null;
    }
}
