package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.BaseResult;
import au.com.telstra.simcardactivator.dto.SimCardActivateReq;
import au.com.telstra.simcardactivator.dto.SimCardActivateResp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;
    private SimCardActivateReq activateReq;
    private ResponseEntity<BaseResult<SimCardActivateResp>> activateResp;

    @Given("I have a new SIM card with iccid {string} and email {string}")
    public void haveANewSimCard(String iccid, String email) {
        activateReq = new SimCardActivateReq(iccid, email);
        System.out.println(activateReq);
    }

    @When("I request to activate the SIM card")
    public void requestToActivateTheSimCard() {
        activateResp = restTemplate.exchange(
                "http://localhost:8080/activate",
                HttpMethod.POST,
                new HttpEntity<>(activateReq),
                new ParameterizedTypeReference<BaseResult<SimCardActivateResp>>() {
                }
        );
    }

    @Then("the sim card should be activated")
    public void theSimCardShouldBeActivated() {
        Assert.assertTrue(activateResp.hasBody());
        BaseResult<SimCardActivateResp> result = activateResp.getBody();
        Assert.assertEquals(result.getCode(), 200);
        Assert.assertEquals(result.getMessage(), "Success");
        Assert.assertTrue(result.getData().getSuccess());
    }

    @Then("the sim card should not be activated")
    public void theSimCardShouldNotBeActivated() {
        Assert.assertTrue(activateResp.hasBody());
        BaseResult<SimCardActivateResp> result = activateResp.getBody();
        Assert.assertEquals(result.getCode(), 200);
        Assert.assertEquals(result.getMessage(), "Success");
        Assert.assertFalse(result.getData().getSuccess());
    }
}