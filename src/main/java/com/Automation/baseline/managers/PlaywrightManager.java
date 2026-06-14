package com.Automation.baseline.managers;

import java.util.ArrayList;
import java.util.List;

import com.Automation.baseline.enums.EnvironmentType;
import com.Automation.baseline.enums.WebBrowserType;
import com.Automation.baseline.exception.CustomException;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightManager {

    public static WebBrowserType webBrowserType;
    public static EnvironmentType environmentType;

    // ThreadLocal Variables
    private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public PlaywrightManager() {
        webBrowserType = FileReaderManager.getInstance()
                .getConfigReader()
                .getWebBrowser();

        environmentType = FileReaderManager.getInstance()
                .getConfigReader()
                .getEnvironment();
    }

    /**
     * Initialize Playwright
     */
    public static Page intializePlaywright() {

        try {

            if (tlPlaywright.get() == null) {
                tlPlaywright.set(Playwright.create());
            }

            tlPage.set(intializeBrowser());

            return tlPage.get();

        } catch (Exception e) {
            throw new CustomException("Playwright is not created");
        }
    }

    /**
     * Initialize Browser
     */
    public static Page intializeBrowser() {

        try {

            Browser browser;

            switch (webBrowserType) {

                case FIREFOX:
                    tlContext.set(
                            tlPlaywright.get()
                                    .firefox()
                                    .launch()
                                    .newContext());
                    break;

                case CHROME:

                    BrowserType.LaunchOptions launchOptions =
                            new BrowserType.LaunchOptions();

                    launchOptions.setHeadless(false);
                    launchOptions.setChannel("chrome");

                    List<String> args = new ArrayList<>();
                    args.add("--start-maximized");

                    launchOptions.setArgs(args);

                    browser = tlPlaywright.get()
                            .chromium()
                            .launch(launchOptions);

                    tlContext.set(
                            browser.newContext(
                                    new Browser.NewContextOptions()
                                            .setViewportSize(null)));

                    break;

                case WEBKIT:

                    tlContext.set(
                            tlPlaywright.get()
                                    .webkit()
                                    .launch()
                                    .newContext());

                    break;

                case CHROMIUM:

                    tlContext.set(
                            tlPlaywright.get()
                                    .chromium()
                                    .launch()
                                    .newContext());

                    break;

                case EDGE:

                    BrowserType.LaunchOptions edgeOptions =
                            new BrowserType.LaunchOptions();

                    edgeOptions.setHeadless(false);
                    edgeOptions.setChannel("msedge");

                    browser = tlPlaywright.get()
                            .chromium()
                            .launch(edgeOptions);

                    tlContext.set(browser.newContext());

                    break;

                default:
                    throw new CustomException("Invalid Browser Type");
            }

            tlPage.set(tlContext.get().newPage());

            return tlPage.get();

        } catch (Exception e) {
            throw new CustomException("Browser initialization failed");
        }
    }

    /**
     * Get Current Thread Page
     */
    public static Page getPage() {
        return tlPage.get();
    }

    /**
     * Set Current Thread Page
     */
    public static void setPage(Page page) {
        tlPage.set(page);
    }

    /**
     * Get Current Thread Browser Context
     */
    public static BrowserContext getContext() {
        return tlContext.get();
    }

    /**
     * Get Current Thread Playwright
     */
    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    /**
     * Close Playwright
     */
    public static void closePlaywright() {

        try {

            if (tlPage.get() != null) {
                tlPage.get().close();
            }

            if (tlContext.get() != null) {
                tlContext.get().close();
            }

            if (tlPlaywright.get() != null) {
                tlPlaywright.get().close();
            }

            tlPage.remove();
            tlContext.remove();
            tlPlaywright.remove();

        } catch (Exception e) {
            throw new CustomException("Playwright close failed");
        }
    }
}