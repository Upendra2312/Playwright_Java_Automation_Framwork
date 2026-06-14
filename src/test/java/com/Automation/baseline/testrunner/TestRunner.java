package com.Automation.baseline.testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/Feature" }, glue = { "com.Automation.baseline.steps",
        "com.Automation.baseline.hooks" }, tags = "@Login", plugin = {
                "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    // 🔥 This enables parallel execution at scenario level
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}