package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.SimCard;

public interface ISimCardService {
    SimCard getCard(Long cardId);
    SimCard activateCard(SimCard simCard);
}
