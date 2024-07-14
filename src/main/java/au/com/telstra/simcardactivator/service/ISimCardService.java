package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.SimCard;

public interface ISimCardService {
    boolean activateCard(SimCard simCard);
}
