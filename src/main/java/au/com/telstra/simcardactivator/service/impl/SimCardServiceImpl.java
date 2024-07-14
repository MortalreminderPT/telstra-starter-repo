package au.com.telstra.simcardactivator.service.impl;

import au.com.telstra.simcardactivator.dto.SimCardActuatorActuateReq;
import au.com.telstra.simcardactivator.dto.SimCardActuatorActuateResp;
import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.repository.SimCardRepository;
import au.com.telstra.simcardactivator.service.ISimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class SimCardServiceImpl implements ISimCardService {
    @Value("${services.sim-card-actuator-service.url}")
    private String simCardActuatorServiceUrl;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    SimCardRepository simCardRepository;


    @Override
    public SimCard getCard(Long cardId) {
        Optional<SimCard> simCard = simCardRepository.findById(cardId);
        return simCard.orElse(null);
    }

    @Override
    public SimCard activateCard(SimCard simCard) {
        SimCardActuatorActuateReq actuateReq = new SimCardActuatorActuateReq(simCard.getIccid());
        String url = simCardActuatorServiceUrl + "/actuate";
        ResponseEntity<SimCardActuatorActuateResp> actuateResp =
                restTemplate
                        .postForEntity(url, actuateReq, SimCardActuatorActuateResp.class);
        SimCardActuatorActuateResp actuateResult = actuateResp.getBody();
        if (actuateResult == null) {
            simCard.setActive(false);
        } else {
            simCard.setActive(actuateResult.isSuccess());
        }
        // save in database
        simCard = simCardRepository.save(simCard);
        return simCard;
    }
}