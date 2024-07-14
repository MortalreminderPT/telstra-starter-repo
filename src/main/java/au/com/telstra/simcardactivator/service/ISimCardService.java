package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.SimCardActivate;

public interface ISimCardService {
    boolean activateCard(SimCardActivate activate);
}
