package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.BaseResult;
import au.com.telstra.simcardactivator.dto.SimCardActivateReq;
import au.com.telstra.simcardactivator.dto.SimCardActivateResp;
import au.com.telstra.simcardactivator.dto.SimCardResp;
import au.com.telstra.simcardactivator.model.SimCard;
import au.com.telstra.simcardactivator.service.ISimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimCardController {
    @Autowired
    ISimCardService simCardService;

    @GetMapping(value = "/query")
    public BaseResult<SimCardResp> queryCard(@RequestParam(required = true) Long id) {
        SimCard card = simCardService.getCard(id);
        if (null == card) {
            return BaseResult.error(404, "Queried Card not found.");
        }
        return BaseResult.success(new SimCardResp(card.getIccid(), card.getCustomerEmail(), card.isActive()));
    }

    @PostMapping(value = "/activate")
    public BaseResult<SimCardActivateResp> activateCard(@RequestBody(required = true) SimCardActivateReq activateReq) {
        // valid
        if (null == activateReq.getIccid() || activateReq.getIccid().isEmpty()) {
            return BaseResult.error(401, "Please provide an Iccid.");
        } else if (null == activateReq.getCustomerEmail() || activateReq.getCustomerEmail().isEmpty()) {
            return BaseResult.error(401, "Please provide a customer email.");
        }
        // convert
        SimCard simCard = new SimCard(activateReq.getIccid(), activateReq.getCustomerEmail(), false);
        simCard = simCardService.activateCard(simCard);
        return BaseResult.success(
                new SimCardActivateResp(simCard.isActive(), simCard.getId())
        );
    }
}