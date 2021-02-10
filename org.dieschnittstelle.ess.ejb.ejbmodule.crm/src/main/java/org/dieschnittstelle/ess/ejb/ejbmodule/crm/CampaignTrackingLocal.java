package org.dieschnittstelle.ess.ejb.ejbmodule.crm;

import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.CampaignExecution;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CampaignTrackingLocal {


    public void addCampaignExecution(CampaignExecution campaign);

    // usage of PUT here is due to the fact that we will neither refactor the previous ejb interfaces nor their
    // implementations for Stateless and Singleton beans, hence we need to be able to pass the touchpoint object
    // via the body of the request

    public int existsValidCampaignExecutionAtTouchpoint(long erpProductId,
                                                        AbstractTouchpoint tp);

    // we specify the amount of units to be purchased passing a query parameter

    public void purchaseCampaignAtTouchpoint( long erpProductId,
                                             AbstractTouchpoint tp,  int units);

    public List<CampaignExecution> getAllCampaignExecutions();
}
