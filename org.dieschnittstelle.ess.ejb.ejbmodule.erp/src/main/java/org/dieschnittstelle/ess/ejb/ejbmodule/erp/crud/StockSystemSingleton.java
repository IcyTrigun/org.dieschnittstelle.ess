package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Remote(StockSystemRemote.class)
@WebService(targetNamespace = "http://dieschnittstelle.org/ess/jws", serviceName = "StockSystemAccessWebService", endpointInterface = "org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class StockSystemSingleton implements StockSystemLocal, StockSystemRemote {

    @EJB
    private StockItemCRUDLocal siCrud;

    @EJB
    private PointOfSaleCRUDLocal posCrud;
    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCrud.readPointOfSale((pointOfSaleId));
       StockItem stockItem = siCrud.readStockItem(product,pos);
        if(stockItem==null){
            stockItem = new StockItem(product,pos,units);
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
        List <IndividualisedProductItem> products = new ArrayList<>();
        PointOfSale pos = posCrud.readPointOfSale(pointOfSaleId);
        List<StockItem> stockItems = siCrud.readStockItemsForPointOfSale(pos);
        for (int i = 0; i < stockItems.size(); i++){
            products.add(stockItems.get(i).getProduct());

        }
        return products;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List <IndividualisedProductItem> allProds = new ArrayList<>();
        List <PointOfSale> allPos = posCrud.readAllPointsOfSale();
        for(PointOfSale pos : allPos){
            List <StockItem> stockItems = siCrud.readStockItemsForPointOfSale(pos);
            System.out.println("Liste: "+ stockItems);
            for(StockItem item : stockItems){
                IndividualisedProductItem prod = item.getProduct();
                if(!allProds.contains(prod)){
                    allProds.add(prod);
                }
            }
        }
        return allProds;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCrud.readPointOfSale(pointOfSaleId);
        StockItem si = siCrud.readStockItem(product, pos);
        return  si.getUnits();
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        int totalUnits = 0;
        List<PointOfSale> posList = posCrud.readAllPointsOfSale();
        for(int i = 0; i < posList.size(); i++){
            PointOfSale pos = posCrud.readPointOfSale(posList.get(i).getId());
            if(siCrud.readStockItem(product, pos) != null){
                StockItem si = siCrud.readStockItem(product, pos);
                totalUnits += si.getUnits();
            }
        }
        return totalUnits;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
      return  siCrud.readStockItemsForProduct(product)
                .stream()
                .map(si -> si.getPos().getId())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<StockItem> getCompleteStock() {
        throw new UnsupportedOperationException("getCompleteStock() isn`t supported");
    }
}
