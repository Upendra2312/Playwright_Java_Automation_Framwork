package com.Automation.baseline.utils;

import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import com.Automation.baseline.constant.Constant;
import com.Automation.baseline.exception.CustomException;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * @Author: QA
 */
public class CommonUtils {

    /**
     * @Author: QA Method to navigates to a random sub-category under a
     * random category
     * @param page
     */
    public static void select_random_mega_menu(Page page) {
        Locator mega_menu = page.locator(
                "//ul[contains(@class, 'nav navbar-nav')]//li[contains(@class, 'nav-item') and @role='presentation']");
        int random_menu_number = generateRandomNumberfromGivenNumber(mega_menu.count());
        Locator Category_name_for_report = page.locator(
                "(//ul[contains(@class, 'nav navbar-nav')]//li[contains(@class, 'nav-item') and @role='presentation'])["
                + random_menu_number + "]");
        int index_of_menu = random_menu_number - 1;
        Locator select_mega_menu = mega_menu.nth(index_of_menu);
        if (random_menu_number == 5 || random_menu_number == 6) {
            //PlaywrightUtils.click(select_mega_menu);
            select_random_mega_menu(page);
            ExtentCucumberAdapter.addTestStepLog(
                    "Navigated to " + PlaywrightUtils.getText(Category_name_for_report) + "' Category successfully");
        } else {
            Locator sub_Category = page
                    .locator("(//ul[contains(@class, 'nav navbar-nav')]//li[contains(@class, 'nav-item dropdown')])["
                            + random_menu_number + "]//a[@class='dropdown-link']");
            int random_sub_menu_number = generateRandomNumberfromGivenNumberofSubmenus(sub_Category.count());
            if (random_sub_menu_number == 1) {
                random_sub_menu_number = random_sub_menu_number + 1;
            }
            int index_of_sub_menu = random_sub_menu_number - 1;
            Locator select_sub_menu = sub_Category.nth(index_of_sub_menu);
            PlaywrightUtils.hover(select_mega_menu);
            PlaywrightUtils.waitForOneSec();
            PlaywrightUtils.click(select_sub_menu);
            ExtentCucumberAdapter
                    .addTestStepLog("Navigated to '" + select_sub_menu.innerText() + "' Sub-Category under '"
                            + PlaywrightUtils.getText(Category_name_for_report) + "' Category successfully");
        }
    }

    /**
     * @Author: QA Method to navigates to a random sub-category under a
     * random category
     * @param page
     */
    public static void select_random_clp_category_from_mega_menu(Page page) {
        Locator mega_menu = page.locator(
                "//ul[contains(@class, 'nav navbar-nav')]//li[contains(@class, 'nav-item dropdown') and @role='presentation']");
        int random_menu_number = generateRandomNumberfromGivenNumber(mega_menu.count());
        Locator Category_name_for_report = page.locator(
                "(//ul[contains(@class, 'nav navbar-nav')]//li[contains(@class, 'nav-item dropdown') and @role='presentation']//a)["
                + random_menu_number + "]");
        int index_of_menu = random_menu_number - 1;
        Locator select_mega_menu = mega_menu.nth(index_of_menu);
        PlaywrightUtils.click(select_mega_menu);
        ExtentCucumberAdapter
                .addTestStepLog("Navigated to '" + select_mega_menu.innerText() + "' Category successfully");
        //}
    }

    /**
     * @Author: QA Method to select random category tile
     * @param page
     * @return
     */
    public static Locator select_random_category_tile(Page page) {
        Locator category_tiles = page.locator("//h5[@class='category-name']");
        int random_category_tile = generateRandomNumberfromGivenNumber(category_tiles.count());
        int index_of_menu = random_category_tile - 1;
        Locator select_category_tiles = category_tiles.nth(index_of_menu);
        PlaywrightUtils.waitForMoreSec(1);
        return select_category_tiles;
    }

    /**
     * @Author: QA Method to return footer link
     * @param page
     * @return
     */
    public static Locator select_random_footer_links(Page page) {
        Locator footer_links = page.locator("//footer//div[@class='footer-info']//ul//li//a");
        int random_footer_link = generateRandomNumberfromGivenNumber(footer_links.count());
        if (random_footer_link > 12) {
            random_footer_link = random_footer_link - 8;
        }
        //System.out.println(random_footer_link);
        int index_of_footer_link = random_footer_link - 1;
        //System.out.println(index_of_footer_link);
        Locator generated_footer_link = footer_links.nth(index_of_footer_link);
        return generated_footer_link;
    }

    /**
     * @Author: QA Method to return social media footer link
     * @param page
     * @return
     */
    public static Locator social_media_random_footer_link(Page page) {
        Locator social_media_footer_links = page.locator("//footer//ul[contains(@class, 'social-links')]//li//a");
        int social_media_random_footer_link = generateRandomNumberfromGivenNumber(social_media_footer_links.count());
        if (social_media_random_footer_link == 4 || social_media_random_footer_link == 3
                || social_media_random_footer_link == 2) {
            social_media_random_footer_link = 1;
        }
        int index_of_social_media_footer_link = social_media_random_footer_link - 1;
        Locator generated_social_media_footer_link = social_media_footer_links.nth(index_of_social_media_footer_link);
        return generated_social_media_footer_link;
    }

    /**
     * Method to get Products List from Products.json file
     *
     * @return - List of Products
     */
    public static List<String> getProducts() {
        String name = "Name";
        List<String> prodList = new ArrayList<>();
        for (Object productObj : (JSONArray) Constant.productsJsonObj.get(Constant.flowName)) {
            JSONObject productJSONObj = (JSONObject) productObj;
            prodList.add((String) productJSONObj.get(name));
        }
        return prodList;
    }

    /**
     * Method to get Quantity of given Product from Products.json
     *
     * @param productName - Name of the Products
     * @return - quantity of the given product
     */
    public static String getProductQuantity(String quoteLineNumber) {
        String name = "QuoteLineItemNumber";
        String quantity = "Quantity";
        for (Object productObj : (JSONArray) Constant.productsJsonObj.get(Constant.flowName)) {
            JSONObject productJSONObj = (JSONObject) productObj;
            if (quoteLineNumber.equals((String) productJSONObj.get(name))) {
                if (((String) productJSONObj.get(quantity)).matches("[0-9]+")
                        || Integer.parseInt((String) productJSONObj.get(quantity)) > 0) {
                    return (String) productJSONObj.get(quantity);
                } else {
                    throw new CustomException("You must define Quantity as a valid digit in Products.json for '"
                            + quoteLineNumber + "' QuoteLine Item");
                }
            }
        }
        throw new CustomException(
                "For '" + quoteLineNumber + "' QuoteLine Item Quantity is not defined in Products.json file");
    }

    /**
     * Method to get Quantity of given Product from Products.json
     *
     * @param productName - Name of the Products
     * @return - quantity of the given product
     */
    public static String getProductTerm(String quoteLineNumber) {
        String name = "QuoteLineItemNumber";
        String term = "Term";
        for (Object productObj : (JSONArray) Constant.productsJsonObj.get(Constant.flowName)) {
            JSONObject productJSONObj = (JSONObject) productObj;
            if (quoteLineNumber.equals((String) productJSONObj.get(name))) {
                if (((String) productJSONObj.get(term)).matches("[0-9]+")
                        || ((String) productJSONObj.get(term)).equals("NA")) {
                    return (String) productJSONObj.get(term);
                } else {
                    throw new CustomException("You must define Term as a digit or 'NA' in Products.json for '"
                            + quoteLineNumber + "' QuoteLine Item");
                }
            }
        }
        throw new CustomException(
                "For '" + quoteLineNumber + "' QuoteLine Item Term is not defined in Products.json file");
    }

    /**
     * Method to get Trade Discount of given Product from Products.json
     *
     * @param productName - Name of the Products
     * @return - TradeDiscount of the given product
     */
    public static String getProductTradeDiscount(String quoteLineNumber) {
        String name = "QuoteLineItemNumber";
        String tradeDiscount = "LineLevelTD";
        for (Object productObj : (JSONArray) Constant.productsJsonObj.get(Constant.flowName)) {
            JSONObject productJSONObj = (JSONObject) productObj;
            if (quoteLineNumber.equals((String) productJSONObj.get(name))) {
                if (((String) productJSONObj.get(tradeDiscount)).matches("[0-9]+")
                        || ((String) productJSONObj.get(tradeDiscount)).equals("NA")) {
                    return (String) productJSONObj.get(tradeDiscount);
                } else {
                    throw new CustomException("You must define LineLevelTD as a digit or 'NA' in Products.json for '"
                            + quoteLineNumber + "' QuoteLine Item");
                }
            }
        }
        throw new CustomException(
                "For '" + quoteLineNumber + "' QuoteLine Item LineLevelTD is not defined in Products.json file");
    }

    /**
     * Method to get difference b/w Dates(in Months)
     *
     * @param earlier - Earlier Date in YYYY-MM-DD
     * @param later - Later Date in YYYY-MM-DD
     * @return - Difference in Months and Days(Months#Days)
     */
    public static String getDifferenceBetweenDates(String earlier, String later) {

        later = CommonUtils.addDays(later, 1);
        LocalDate earlierFullDate = LocalDate.parse(earlier);
        LocalDate laterFullDate = LocalDate.parse(later);
        Period d = Period.between(earlierFullDate, laterFullDate);
        if (d.getYears() <= 0 && d.getMonths() <= 0 && d.getDays() <= 0) {
            throw new CustomException(
                    "Invalid Dates(Required format: 'yyyy-MM-dd') '" + earlier + "' and '" + later + "'");
        }
        int months = 0;
        if (d.getYears() > 0) {
            months = d.getYears() * 12 + d.getMonths();
        } else {
            months = d.getMonths();
        }
        return months + "#" + d.getDays();
    }

    /**
     * Method for Returning Searched App Locator by Given App Name
     *
     * @param value - App Name
     * @return - Element Locator
     */
    public static Locator getSearchedAppLocator(String value) {
        return  PlaywrightManager.getPage().locator(String.format("//a[@data-label='%s']//lightning-formatted-rich-text/span", value));
    }

    /**
     * Method for Opening Salesforce Object in Browser by given Record Id
     *
     * @param recordId - Record Id from Salesforce
     * @param page - Page on which you are navigating to URL
     */
    public static void getRecordInSalesforce(String recordId, Page page) {
        try {
            if (Constant.baseURL != null) {
                page.navigate(Constant.baseURL + recordId);
                PlaywrightUtils.waitForPageToLoad(page);
            } else {
                throw new CustomException("Base URL not Intialized");
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method for Returning Div Tag Locator By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getDivTagWebElementByExactText(String value, Page page) {
        return page.locator(String.format("//div[text()='%s']", value));
    }

    /**
     * Method for Returning button for User logout
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getUserLogoutButton(String value, Page page) {
        return page.locator(String.format("//a[text()='Log out as %s']", value));
    }

    /**
     * Method for Returning Div Tag Locator By Given Text from Locator
     *
     * @param value - Dynamic Text value
     * @param locator - Locator on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getDivTagWebElementByExactText(String value, Locator locator) {
        return locator.locator(String.format("//div[text()='%s']", value));
    }

    /**
     * Method for Returning option of drop down Locator By Given Text from
     * Locator
     *
     * @param value - Dynamic Text value
     * @param locator - Locator on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getSelectOptionElementByExactText(String value, Page page) {
        return page.locator(String.format("//select[@name= 'sort-order']//option[contains(text(), '%s')]", value));
    }

    /**
     * Method for Returning Div Tag Locator from FrameLocator By Given Text
     *
     * @param value - Dynamic Text value
     * @param frame - FrameLocator in which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getDivTagWebElementByExactText(String value, FrameLocator frame) {
        return frame.locator(String.format("//div[text()='%s']", value));
    }

    /**
     * Method for Returning Locator for Account Option
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getAccountNameForOpportunity(String value, Page page) {
        return page.locator(String.format("(//lightning-base-combobox-formatted-text[@title='%s'])[1]", value));
    }

    /**
     * Method to add days in the given date then returns the new date in String
     *
     * @param date - Date in which Days should be added
     * @param days - No. of Days to add
     * @return - New Date
     */
    public static String addDays(String date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_MONTH, days);
        return sdf.format(cal.getTime());
    }

    /**
     * Method to add months in the given date then returns the new date in
     * String
     *
     * @param date - Date in which Months should be added
     * @param days - No. of Months to add
     * @return - New Date
     */
    public static String addMonths(String date, int months) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH, months);
        return sdf.format(cal.getTime());
    }

    /**
     * Method for Returning Locator of Field Value for Opportunity And Quote
     *
     * @param value - Field Name
     * @param page - Browser Page
     * @return Element Locator
     */
    public static Locator getFieldValueOnOpportunityAndQuotePage(String value, Page page) {
        return page.locator(String.format("//records-record-layout-block//span[text()='%s']/../..//a//span", value));
    }

    /**
     * Method for adding key-value in given map
     *
     * @param key - Key which should be added in map
     * @param value - Value which should be added in map
     * @param mapName = Map Name to add Key & Value
     */
    public static void addKeyValueInMap(String key, String value, String mapName) {
        if (Constant.comparisonMap.containsKey(mapName)) {
            Constant.comparisonMap.get(mapName).put(key, value);
        } else {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(key, value);
            Constant.comparisonMap.put(mapName, map);
        }
    }

    /**
     * Method for adding key-value in given map
     *
     * @param key - Key which should be added in map
     * @param value - Value which should be added in map
     * @param mapName = Map Name to add Key & Value
     * @param map - Map(Map of Maps) in which you want to add map of key value
     */
    public static void addKeyValueInGivenMap(String key, Object value, String mapName,
            Map<String, Map<String, Object>> map) {
        if (map.containsKey(mapName)) {
            map.get(mapName).put(key, value);
        } else {
            HashMap<String, Object> temp = new HashMap<String, Object>();
            temp.put(key, value);
            map.put(mapName, temp);
        }
    }

    /**
     * Method to Compare All Maps stored in ComparisonMap(Map of Maps)
     *
     * @param condition - Matches or Unmatches
     */
    public static void compareMaps(String condition) {
        List<String> keys = new ArrayList<String>(Constant.comparisonMap.keySet());
        String mapString = keys.get(0);
        for (int i = 1; i < keys.size(); i++) {
            if (condition.equals("mismatches")) {
                Assert.assertNotSame(Constant.comparisonMap.get(mapString), Constant.comparisonMap.get(keys.get(i)));
            } else {
                Assert.assertEquals(Constant.comparisonMap.get(mapString), Constant.comparisonMap.get(keys.get(i)));
            }
        }
    }

    /**
     * Method for Returning Locator for Edit Button for a Field in Salesforce by
     * Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return Element Locator
     */
    public static Locator getEditButtonUsingFieldName(String value, Page page) {
        return page.locator(String.format("//span[text()='%s']/../..//button[contains(@title,'Edit')]", value));
    }

    /**
     * Method for Returning Locator for Opening Edit Dropdown for Field
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return Element Locator
     */
    public static Locator openDropdown(String value, Page page) {
        return page.locator(String.format("(//button[contains(@aria-label,'%s')])[1]", value));
    }

    /**
     * Method for Returning Span Tag WebElement By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getSpanTagWebElementByExactText(String value, Page page) {
        return page.locator(String.format("//span[text()='%s']", value));
    }

    /**
     * Method for Returning Span Tag WebElement By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getOptionValueWithSpanTagWebElementByExactText(String value, Page page) {
        return page.locator(String.format("//lightning-base-combobox-item//span[text()='%s']", value));
    }

    /**
     * Method for Returning Anchor Tag WebElement By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getAnchorTagWebElementByExactText(String value, Page page) {
        page.locator(String.format("//a[text()='%s']", value)).all();
        //System.out.println(page.locator(String.format("//a[text()='%s']", value)).count());
        return page.locator(String.format("//a[text()='%s']", value));
    }

    /**
     * Method for Returning Anchor Tag WebElement By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getAnchorTagWebElementByExactTextRandomly(String value, Page page) {
        return page.locator(String.format("//a[text()='%s']", value));
    }

    /**
     * Method for Returning Eud User Option by Given Name
     *
     * @param value - Dynamic Text value
     * @param page - Locator on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getEndUserOption(String value, Locator locator) {
        return locator.locator(String.format("div[data-label='%s']", value));
    }

    /**
     * Method for Returning Span Tag WebElement By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getSpanTagWebElementByContainsText(String value, Page page) {
        return page.locator(String.format("//span[contains(text(),'%s')]", value));
    }

    /**
     * Method for Returning Locator for Opening Edit Dropdown for Field
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getOptionAfterSearch(String value, Page page) {
        return page.locator(String.format("(//lightning-base-combobox-formatted-text[@title='%s'])[1]", value));
    }

    /**
     * Method for Returning Field's Value Locator By Given Text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getFieldValueLocator(String value, Page page) {
        return page.locator(String.format("(//span[text()='%s'])[1]/following::lightning-formatted-text[1]", value));
    }

    /**
     * @Author: QA Method for Returning Categories links by given text which
     * is on the home page
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getCategoriesLocators(String value, Page page) {
        return page.locator(String.format("//h5[contains(text(),'%s')]", value));
    }

    /**
     * @Author: QA Method for Returning footer links by given text which is
     * on the home page
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getFooterLinkLocators(String value, Page page) {
        return page.locator(String.format("//footer//ul//li//a[text()='%s']", value));
    }

    /**
     * @Author: QA Method for Returning social media footer links by given
     * text which is on the home page
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getSocialMediaFooterLinkLocators(String value, Page page) {
        return page.locator(String
                .format("//footer//ul[contains(@class, 'social-links')]//li//a[contains(@aria-label, '%s')]", value));
    }

    /**
     * Method for Returning Type of Opportunity by given text
     *
     * @param value - Dynamic Text value
     * @param page - Page on which you are looking for Element
     * @return - Element Locator
     */
    public static Locator getTypeOfOpportunity(String value, Page page) {
        return page.locator(String.format(
                "//div[@class='changeRecordTypeOptionRightColumn']/span[text()='%s']/preceding::span[1]", value));
    }

    /**
     * Method for Verifying Field Value
     *
     * @param value - Expected Value
     * @param fieldName - Field Name having Actual Value
     * @param page - Page having the Field
     */
    public static void verifyFieldValue(String value, String fieldName, Page page) {
        try {
            PlaywrightUtils.waitForAnElement(getFieldValueLocator(fieldName,  PlaywrightManager.getPage()));
            Assert.assertEquals(value, PlaywrightUtils.getText(getFieldValueLocator(fieldName,  PlaywrightManager.getPage())));
            Constant.result = "Value for '" + fieldName + "' field is '" + value + "' verified successfully";
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to get SOQL Field Value from Constant
     *
     * @param fieldName - Field Name you want extract from API Response
     * @return Object having the value
     */
    public static Object getSOQLFieldValueFromAPIConstant(String fieldName) {
        if (Constant.recordsFromAPI.get(0).containsKey(fieldName)) {
            return Constant.recordsFromAPI.get(0).get(fieldName);
        } else {
            throw new CustomException(fieldName + " Field Value not returned from API");
        }
    }

    /**
     * Method to Set Value on Multiple Element(Input Fields)
     *
     * @param values - Values Array
     * @param fieldNames - Field Names Array
     */
    public static void setValueInMultipleFields(String[] values, String[] fieldNames) {
        try {
            if (values.length != fieldNames.length) {
                throw new CustomException("Values and Fields count mismatch");
            }
            for (int i = 0; i < values.length; i++) {
                PlaywrightUtils.setValue(values[i].trim(),
                        PlaywrightUtils.getElement(fieldNames[i].trim(),  PlaywrightManager.getPage()));
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Select Multiple Dropdowns
     *
     * @param options - Option Array
     * @param fieldNames - Dropdown Names Array
     */
    public static void selectMultipleDropdowns(String[] options, String[] fieldNames) {
        try {
            if (options.length != fieldNames.length) {
                throw new CustomException("Options and Fields count mismatch");
            }
            for (int i = 0; i < options.length; i++) {
                PlaywrightUtils.selectByLabelFromDropdown(options[i].trim(),
                        PlaywrightUtils.getElement(fieldNames[i].trim(),  PlaywrightManager.getPage()));
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method for Search and Select from Dropdown
     *
     * @param value - Value to Select
     * @param field - Field Name
     */
    public static void searchAndSelect(String value, String field) {
        try {
            PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(field,  PlaywrightManager.getPage()));
            PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(field,  PlaywrightManager.getPage()));
            PlaywrightUtils.setValueUsingKeyboard(value, PlaywrightUtils.getElement(field,  PlaywrightManager.getPage()));
            PlaywrightUtils.waitForSec();
            PlaywrightUtils.keyPress("Backspace",  PlaywrightManager.getPage());
            try {
                PlaywrightUtils.click(CommonUtils.getAccountNameForOpportunity(value,  PlaywrightManager.getPage()));
            } catch (Exception e) {
                PlaywrightUtils.waitForSec();
                PlaywrightUtils.keyPress("Backspace",  PlaywrightManager.getPage());
                PlaywrightUtils.click(CommonUtils.getAccountNameForOpportunity(value,  PlaywrightManager.getPage()));
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method for Search and Select Multiple Dropdown
     *
     * @param values - Values to Select
     * @param fields - Field Names
     */
    public static void searchAndSelectMultipleDropdown(String[] values, String[] fields) {
        try {
            if (values.length != fields.length) {
                throw new CustomException("Options and Fields count mismatch");
            }
            for (int i = 0; i < values.length; i++) {
                searchAndSelect(values[i].trim(), fields[i].trim());
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to round off float to 2 decimal places
     *
     * @param number - input number
     * @return rounded off number
     */
    public static float roundOffFloatToTwoDecPlace(float number) {
        return Float.parseFloat(new DecimalFormat("0.00").format(number));
    }

    /**
     * Method to round off float to 2 decimal places
     *
     * @param number - input number
     * @return rounded off number
     */
    public static String roundOffFloatToTwoDecPlaceToString(float number) {
        return String.valueOf(new DecimalFormat("0.00").format(number));
    }


    

    /**
     * Method for Verifying Field Value located under frame
     *
     * @param value - Expected Value
     * @param fieldName - Field Name having Actual Value
     * @param frame - FrameLocator
     * @param page - Page having the Field
     */
    public static void verifyFieldValue(String value, String fieldName, String frame, Page page) {
        Constant.FRAME = PlaywrightUtils.getFrame(frame,  PlaywrightManager.getPage());
        PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(fieldName, Constant.FRAME));
        Assert.assertEquals(value, PlaywrightUtils.getText(PlaywrightUtils.getElement(fieldName, Constant.FRAME)));
    }

    /**
     * Method to generate Random Number of given Digits
     *
     * @param digits - no. of digits
     * @return Random Number as String
     */
    public static String generateRandomNumber(int digits) {
        if (digits == 0) {
            throw new CustomException("0 digits of number cannot be generated");
        }
        String number = "";
        String temp;
        Random random = new Random();
        for (int i = 0; i < digits; i++) {
            temp = String.valueOf(random.nextInt(10));
            if (i == 0 && temp.equals("0")) {
                temp = "1";
            }
            number += temp;
        }
        return number;
    }

    /**
     * Method for generate a random number
     *
     * @param no_of_elements
     * @return
     */
    public static int generateRandomNumberfromGivenNumber(int no_of_elements) {
        Random random = new Random();
        int generated_random_number = random.nextInt(no_of_elements);
        // //System.out.println(generated_random_number);
        if (generated_random_number == 0) {
            return generated_random_number + 1;
        } else {
            return generated_random_number;
        }
    }

    /**
     * Method to generate a random number between 5 and 10 (inclusive).
     *
     * @return - Random number between 5 and 10
     */
    public static int generateRandomNumberFromgreaterThanfive(int no_of_elements) {
        Random random = new Random();
        // Generate a random number between 5 and 10 (inclusive)
        int generated_random_number = 5 + random.nextInt(no_of_elements); // 5 to 10

        return generated_random_number;
    }

    /**
     * Method for generate a random number
     *
     * @param no_of_elements
     * @return
     */
    public static int generateRandomNumberfromGivenNumberofSubmenus(int no_of_elements) {
        Random random = new Random();
        int generated_random_number = random.nextInt(no_of_elements);
        if (generated_random_number == 0 || generated_random_number == 1) {
            return generated_random_number + 1;
        } else {
            return generated_random_number;
        }
    }

    /**
     * Method to logout from given user in salesforce
     *
     * @param user - user name
     */
    public static void userLogout(String user) {
        try {
            PlaywrightUtils.waitForAnElement(getUserLogoutButton(user,  PlaywrightManager.getPage()));
            PlaywrightUtils.click(getUserLogoutButton(user,  PlaywrightManager.getPage()));
            PlaywrightUtils.waitForMoreSec(2);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    
    /**
     * Method for Returning Anchor Tag Locator By Given Text
     *
     * @param value - Text
     * @param page - Browser Page
     * @return Element Locator
     */
    public static Locator getAnchorTagWebElementByText(String value, Page page) {
        return page.locator(String.format("//a[contains(text(),'%s')]", value));
    }

    /**
     * Method for Returning Locator for Category by Given Category Name
     *
     * @param CategoryName - Category Name
     * @param page - Browser Page
     * @return Category Element Locator
     */
    public static Locator findCategoryByText(String categoryName, Page page) {
        return getAnchorTagWebElementByText(categoryName, page);
    }

    /**
     * Method for Returning Locator for Entity By Name
     *
     * @param value - Entity Name
     * @param page - Browser Page
     * @return Entity Element Locator
     */
    public static Locator getEntityByName(String value, Page page) {
        return page.locator(String.format("//th//a[@title='%s']", value));
    }

    /**
     * Method for Returning H1 Tag Locator By Given Text
     *
     * @param value - Entity Name
     * @param page - Browser Page
     * @return - Entity Locator
     */
    public static Locator getH1TagWebElementByText(String value, Page page) {
        return page.locator(String.format("//h1[contains(text(),'%s')]", value));
    }

    /**
     * Method for Returning Locator for Product by Given Text
     *
     * @param productName - Product Name
     * @param page - Browser Page
     * @return Product Locator
     */
    public static Locator findProductByText(String productName, Page page) {
        return getSpanTagWebElementByText(productName, page);
    }

    /**
     * Method for Returning Span Tag Locator By Given Text
     *
     * @param value - Entity Name
     * @param page - Browser Page
     * @return Entity Locator
     */
    public static Locator getSpanTagWebElementByText(String value, Page page) {
        return page.locator(String.format("//span[contains(text(),'%s')]", value));
    }

    /**
     * Method for Returning Button Tag Locator By Given Text
     *
     * @param value - Entity Name
     * @param page - Browser Page
     * @return - Entity Locator
     */
    public static Locator getButtonTagWebElementByText(String value, Page page) {
        return page.locator(String.format("//button[contains(text(),'%s')]", value));
    }

    /**
     * Method for Returning Anchor Tag Locator By Given Text
     *
     * @param value - Text
     * @param page - Browser Page
     * @return Element Locator
     */
    public static Locator findCategoryByExactText(String value, Page page) {
        return page.locator(String.format("//ul[@class='nav navbar-nav level-1']//a[not(contains(@id, 'newarrivals-electronics')) and text()='%s']", value));
    }

    /**
     * Method for Returning Anchor Tag Locator By Given id
     *
     * @param value - Text
     * @param page - Browser Page
     * @return Element Locator
     */
    public static Locator findCategoryByExactId(String value, Page page) {
        return page.locator(String.format("//ul[@class='nav navbar-nav level-1']//a[@id='%s']", value));
    }

    /**
     * Method selecting the Product by Given text
     *
     * @param product - Product Name
     */
    public static void productSelect(String product) {
        try {
            PlaywrightUtils.waitForAnElement(CommonUtils.getAnchorTagWebElementByExactText(product,  PlaywrightManager.getPage()));
            PlaywrightUtils.click(CommonUtils.getAnchorTagWebElementByExactText(product,  PlaywrightManager.getPage()));
            Constant.result = "'" + product + "' Product selected successfully";
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Method to select a random product form the list of products
     *
     */
    public static void selectARandomProductFromTheList(String product) {
        Locator locator = CommonUtils.getAnchorTagWebElementByExactText(product,  PlaywrightManager.getPage());
        //System.out.println(locator);
        int howMayProductsLoaded = locator.count();
        Random random = new Random();
        int randomIntBounded = random.nextInt(howMayProductsLoaded);
        Locator singleproduct = locator.nth(randomIntBounded);
        PlaywrightUtils.waitForAnElement(singleproduct);
        PlaywrightUtils.click(singleproduct);
        Constant.result = "'" + product + "' Product selected successfully";

    }

    /**
     * Method for Returning Locator for Product by Given Text for Verification
     *
     * @param product - Product Name
     * @param page - Browser Page
     * @return - Entity Locator
     */
    public static Locator verifyProductOnDetailPage(String product, Page page) {
        return page.locator(String.format("//h1[@class='product-name' and text()='%s']", product));
    }

    /**
     * Method to find the searched app name of the given search box
     *
     * @param appName - Name of the app
     * @param Value -
     * @return - app Loactor
     */
    public static Locator AppNameSearchedontheSalesforce(String app, Page page) {
        return page.locator(String.format("//a[@data-label='%s']//b", app));
    }

    /**
     * Method to find the searched app name of the given search box
     *
     * @param appName - Name of the app
     * @param Value -
     * @return - app Loactor
     */
    public static Locator generatedLinkLocator(String value, Page page) {
        return page.locator(String.format("//a[text()='%s']", value));
    }

    /**
     * Method for Product Verify
     *
     * @param product - Product Name
     */
    public static void verifyProduct(String product) {
        try {
            PlaywrightUtils.waitForAnElement(verifyProductOnDetailPage(product,  PlaywrightManager.getPage()));
            Assert.assertTrue(verifyProductOnDetailPage(product,  PlaywrightManager.getPage()).isVisible());
            Constant.result = "'" + product + "' Product displayed successfully";
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method for Returning Locator for Option tag by Given Text
     *
     * @param product - Product Name
     * @param page - Browser Page
     * @return - Entity Locator
     */
    public static Locator getOptionTagWebElementByExactText(String option, Page page) {
        return page.locator(String.format("//option[contains(text(),'%s')]", option));
    }

    /**
     * Method for Returning Locator for Option tag by Given Text
     *
     * @param product - Product Name
     * @param page - Browser Page
     * @return - Entity Locator
     */
    public static Locator getOptionTagForSizeWebElementByExactText(String option, Page page) {
        return page.locator(String.format("//select[@id='size-1']//option[text()='%s']", option));
    }

    /**
     * Method for Returning Locator for Option tag by Given Text
     *
     * @param product - Product Name
     * @param page - Browser Page
     * @return - Entity Locator
     */
    public static Locator getOptionTagForQuantityWebElementByExactText(String option, Page page) {
        return page.locator(String.format("//select[@id='quantity-1']//option[text()='%s']", option));
    }

    /**
     * Method to round off double to 2 decimal places
     *
     * @param number - input number
     * @return - round off number
     */
    public static double roundOffDoubleToTwoDecPlace(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    /**
     * Method to round off double to 2 decimal places
     *
     * @param number - input number
     * @return - round off number
     */
    public static String roundOffDoubleToTwoDecPlaceToString(double number) {
        return String.valueOf(Math.round(number * 100.0) / 100.0);
    }

    /**
     * Method to round off double to 2 decimal places
     *
     * @param number - input number
     * @return - round off number
     */
    public static String roundOffDoubleToOneDecPlaceToString(double number) {
        return String.valueOf(Math.round(number * 10.0) / 10.0);
    }

    /**
     * Method to read the JSON file
     *
     * @param filePath - JSON file path
     * @return - returns JSON Object
     */
    public static JSONObject getJsonData(String filePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = parser.parse(reader);
            return (JSONObject) obj;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());

        }
    }

    public static void select_random_filter_options(Page page) {
        PlaywrightUtils.waitForMoreSec(2);
        if (PlaywrightUtils.getElement("filteroptions",  PlaywrightManager.getPage()).nth(
                generateRandomNumberfromGivenNumber(PlaywrightUtils.getElement("filteroptions",  PlaywrightManager.getPage()).count())
                - 1)
                .isEnabled()) {
            PlaywrightUtils.click(
                    PlaywrightUtils.getElement("filteroptions",  PlaywrightManager.getPage()).nth(generateRandomNumberfromGivenNumber(
                            PlaywrightUtils.getElement("filteroptions",  PlaywrightManager.getPage()).count()) - 1));
            PlaywrightUtils.addScreenshotToReport();
            //System.out.println("The filter selected successfully");
            ExtentCucumberAdapter.addTestStepLog("The filter selected successfully");
        } else {
            //System.out.println("The element is unavailable");
            select_random_filter_options( PlaywrightManager.getPage());
        }
        PlaywrightUtils.waitForMoreSec(1);
    }
}
