package au.com.telstra.simcardactivator.service.impl;

import au.com.telstra.simcardactivator.dto.SimCardActuatorActuateReq;
import au.com.telstra.simcardactivator.dto.SimCardActuatorActuateResp;
import au.com.telstra.simcardactivator.model.SimCardActivate;
import au.com.telstra.simcardactivator.service.ISimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimCardServiceImpl implements ISimCardService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${services.sim-card-actuator-service.url}")
    private String simCardActuatorServiceUrl;

    @Override
    public boolean activateCard(SimCardActivate activate) {
        SimCardActuatorActuateReq actuateReq = new SimCardActuatorActuateReq(activate.getIccid());
        String url = simCardActuatorServiceUrl + "/actuate";
        ResponseEntity<SimCardActuatorActuateResp> actuateResp =
                restTemplate
                        .postForEntity(url, actuateReq, SimCardActuatorActuateResp.class);
        SimCardActuatorActuateResp actuateResult = actuateResp.getBody();
        if (actuateResult == null) {
            return false;
        } else {
            return actuateResult.getSuccess();
        }
    }
}
