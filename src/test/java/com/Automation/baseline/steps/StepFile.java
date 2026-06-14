package com.Automation.baseline.steps;

//import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Assert;
import com.Automation.baseline.constant.Constant;
import com.Automation.baseline.managers.PlaywrightManager;
import com.Automation.baseline.pageobjects.LoginPage;
import com.Automation.baseline.utils.PlaywrightUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepFile {

    LoginPage login = new LoginPage();

    @When("User login with {string} and {string}")
    public void user_login_with_username_and_password(String username, String password) {
        login.enterLoginDetails(username, password);
    }

    @Then("User should be navigated to {string} page")
    public void user_navigated_to_dashboard(String expectedPage) {
        Assert.assertTrue(PlaywrightUtils.getElement(expectedPage,  PlaywrightManager.getPage()).isDisabled());

    }

     @When("User Search with {string}")
    public void user_searchwithvalidkeywords(String product) {
        login.SearchaProduct(product);
    }

    
}
