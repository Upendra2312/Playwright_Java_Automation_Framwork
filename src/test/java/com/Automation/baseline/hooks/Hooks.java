package com.Automation.baseline.hooks;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.Automation.baseline.constant.Constant;
import com.Automation.baseline.dataprovider.LocatorsFileReader;
import com.Automation.baseline.managers.FileReaderManager;
import com.Automation.baseline.managers.PlaywrightManager;
import com.Automation.baseline.utils.ExcelUtils;
import com.Automation.baseline.utils.PlaywrightUtils;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.microsoft.playwright.Page;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) throws IOException, ParseException {

        Constant.currentScenario = scenario;

        LocatorsFileReader.readLocatorProperties();
        ExcelUtils.openStream();

        new PlaywrightManager();

        PlaywrightManager.intializePlaywright();

        PlaywrightUtils.navigateToURL(
                FileReaderManager.getInstance()
                        .getConfigReader()
                        .getStoreUrl(),
                PlaywrightManager.getPage());

        ExtentCucumberAdapter.addTestStepLog(
                "Navigated to '"
                        + FileReaderManager.getInstance()
                                .getConfigReader()
                                .getStoreUrl()
                        + "' URL successfully");
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {

        try {

            if (scenario.isFailed()) {

                scenario.log("Test Case Failed");

                PlaywrightUtils.addScreenshotToReport();

                Page page = PlaywrightManager.getPage();

                if (page != null && !page.isClosed()) {

                    byte[] screenshot = page.screenshot();

                    scenario.attach(
                            screenshot,
                            "image/png",
                            scenario.getName().replaceAll(" ", "_"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            PlaywrightManager.closePlaywright();
        }
    }
}