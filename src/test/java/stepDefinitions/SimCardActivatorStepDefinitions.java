package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dto.BaseResult;
import au.com.telstra.simcardactivator.dto.SimCardActivateReq;
import au.com.telstra.simcardactivator.dto.SimCardActivateResp;
import au.com.telstra.simcardactivator.dto.SimCardResp;
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
    private SimCardActivateReq activateReq = new SimCardActivateReq();
    private Long activateRecordId;

    @Given("a functional sim card")
    public void aFunctionalSimCard() {
        activateReq = new SimCardActivateReq("1255789453849037777", "test@gmail.com");
    }

    @Given("a broken sim card")
    public void aBrokenSimCard() {
        activateReq = new SimCardActivateReq("8944500102198304826", "test@gmail.com");
    }

    @When("I request to activate the SIM card")
    public void requestToActivateTheSimCard() {
        ResponseEntity<BaseResult<SimCardActivateResp>> activateResp;
        activateResp = restTemplate.exchange(
                "http://localhost:8080/activate",
                HttpMethod.POST,
                new HttpEntity<>(activateReq),
                new ParameterizedTypeReference<BaseResult<SimCardActivateResp>>() {
                }
        );
        Assert.assertTrue(activateResp.hasBody());
        BaseResult<SimCardActivateResp> result = activateResp.getBody();
        Assert.assertEquals(result.getCode(), 200);
        Assert.assertEquals(result.getMessage(), "Success");
        activateRecordId = result.getData().getId();
        System.out.println(activateRecordId);
    }

    @Then("the sim card should be activated and its state is recorded to the database")
    public void theSimCardShouldBeActivated() {
        ResponseEntity<BaseResult<SimCardResp>> simCardResp =
                restTemplate.exchange(
                        "http://localhost:8080/query?id=" + activateRecordId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<BaseResult<SimCardResp>>() {
                        }
                );
        Assert.assertTrue(simCardResp.hasBody());
        BaseResult<SimCardResp> result = simCardResp.getBody();
        Assert.assertEquals(result.getCode(), 200);
        Assert.assertEquals(result.getMessage(), "Success");
        Assert.assertTrue(result.getData().isActive());
    }

    @Then("the sim card should not be activated and its state is recorded to the database")
    public void theSimCardShouldNotBeActivated() {
        ResponseEntity<BaseResult<SimCardResp>> simCardResp =
                restTemplate.exchange(
                        "http://localhost:8080/query?id=" + activateRecordId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<BaseResult<SimCardResp>>() {
                        }
                );
        Assert.assertTrue(simCardResp.hasBody());
        BaseResult<SimCardResp> result = simCardResp.getBody();
        Assert.assertEquals(result.getCode(), 200);
        Assert.assertEquals(result.getMessage(), "Success");
        Assert.assertFalse(result.getData().isActive());
    }
}