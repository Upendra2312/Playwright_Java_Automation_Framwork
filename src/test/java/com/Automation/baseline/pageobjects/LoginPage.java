package com.Automation.baseline.pageobjects;

import com.Automation.baseline.constant.Constant;
import com.Automation.baseline.managers.PlaywrightManager;
import com.Automation.baseline.utils.PlaywrightUtils;

/**
 * @author QA Implemented code
 */
public class LoginPage {

    /**
     * Method to enter the user name and password
     *
     * @param UserName
     * @param password
     */
    public void enterLoginDetails(String UserName, String password) {
        PlaywrightUtils.setValue(UserName,
                PlaywrightUtils.scrollToElementandreturn(PlaywrightUtils.getElement("Login Email", PlaywrightManager.getPage())));
        PlaywrightUtils.setValue(password,
                PlaywrightUtils.scrollToElementandreturn(PlaywrightUtils.getElement("Login Password", PlaywrightManager.getPage())));
        PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement("Login Button", PlaywrightManager.getPage()));
        PlaywrightUtils.waitForOneSec();
        PlaywrightUtils.click(PlaywrightUtils.getElement("Login Button", PlaywrightManager.getPage()));
    }

    public void enterCheckorderStatus(String OrderNumber, String OrderEmail, String orderbillingzipcode) {
        PlaywrightUtils.setValue(OrderNumber,
                PlaywrightUtils.scrollToElementandreturn(PlaywrightUtils.getElement("Order Number", PlaywrightManager.getPage())));
        PlaywrightUtils.setValue(OrderEmail,
                PlaywrightUtils.scrollToElementandreturn(PlaywrightUtils.getElement("Order Email", PlaywrightManager.getPage())));
        PlaywrightUtils.setValue(orderbillingzipcode, PlaywrightUtils
                .scrollToElementandreturn(PlaywrightUtils.getElement("Billing Zip Code Of Order", PlaywrightManager.getPage())));
        PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement("Check Status", PlaywrightManager.getPage()));
        PlaywrightUtils.waitForOneSec();
        PlaywrightUtils.click(PlaywrightUtils.getElement("Check Status", PlaywrightManager.getPage()));
    }

    public void SearchaProduct(String Product) {
        PlaywrightUtils.setValue(Product,
                PlaywrightUtils.scrollToElementandreturn(PlaywrightUtils.getElement("search box", PlaywrightManager.getPage())));
        PlaywrightUtils.waitForOneSec();
        PlaywrightUtils.click(PlaywrightUtils.getElement("search box submit", PlaywrightManager.getPage()));

    }

}
