package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.BaseResult;
import au.com.telstra.simcardactivator.dto.SimCardActivateReq;
import au.com.telstra.simcardactivator.dto.SimCardActivateResp;
import au.com.telstra.simcardactivator.model.SimCardActivate;
import au.com.telstra.simcardactivator.service.ISimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimCardController {
    @Autowired
    ISimCardService simCardService;

    @PostMapping(value = "/activate")
    public BaseResult<SimCardActivateResp> activateCard(@RequestBody(required = true) SimCardActivateReq activateReq) {
        // valid
        if (null == activateReq.getIccid() || activateReq.getIccid().isEmpty()) {
            return BaseResult.error(401, "Please provide an Iccid.");
        } else if (null == activateReq.getCustomerEmail() || activateReq.getCustomerEmail().isEmpty()) {
            return BaseResult.error(401, "Please provide a customer email.");
        }
        // convert
        SimCardActivate activate = new SimCardActivate(activateReq.getIccid(), activateReq.getCustomerEmail());
        return BaseResult.success(
                new SimCardActivateResp(simCardService.activateCard(activate))
        );
    }
}