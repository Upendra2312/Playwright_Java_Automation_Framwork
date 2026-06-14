package com.Automation.baseline.utils;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.Automation.baseline.constant.Constant;
import com.Automation.baseline.exception.CustomException;
import com.Automation.baseline.managers.PlaywrightManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.DblclickOptions;
import com.microsoft.playwright.Locator.FocusOptions;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * @Author: QA
 */
public class PlaywrightUtils {

    /**
     * Method to find Element by given Locator
     *
     * @param fieldName - Field Name having locator stored in
     * locators.properties
     * @param page - Page on which you are looking for Element
     * @return - Locator Object having Browser Element
     */
    public static Locator getElement(String fieldName, Page page) {
        // //System.out.println("Coming playwright utils");
        Locator locator = null;
        // //System.out.println("Name of the filed "+fieldName);
        //int counter = 0;
        String locString = Constant.locatorsMap.get(fieldName.replaceAll(" ", "_").toLowerCase());
        // //System.out.println("LocString " + locString);
        if (locString.contains("||")) {
            String[] locators = locString.split("\\|\\|");
            ////System.out.println("if block" + locators.length);
            for (String locator1 : locators) {
               // //System.out.println("\nChecking locator " + ++counter);
                try {
                    locator = getTypeOfElement(locator1, page);
                    ////System.out.println("for loop inside " + locator);
                    PlaywrightUtils.waitForMoreSec(1);
                    if (locator.isVisible()) {
                        //System.out.println("cmong here "+locator1); // Ensure element is visible
                        break; // Exit the loop if the element is found
                    }
                }catch (Exception e) {
                    //if (i == locators.length - 1) {
                    throw new CustomException("Element not found correct your all locators");
                    //}
                    //continue;
                }
                //break;
            }
        } else {
            locator = getTypeOfElement(locString, page);
        }
        return locator;
    }

    /**
     * Method to find Element based on different Locator
     *
     * @param locString - Locator Value
     * @param page - Page on which you are looking for Element
     * @return - Locator Object having Browser Element
     */
    public static Locator getTypeOfElement(String locString, Page page) {
        if (locString.contains("*")) {
            String type = locString.split("*")[0].toLowerCase();
            String value = locString.split("*")[1];
            switch (type) {
                case "alttext":
                    return page.getByAltText(value);
                case "label":
                    return page.getByLabel(value);
                case "placeholder":
                    return page.getByPlaceholder(value);
                case "testid":
                    return page.getByTestId(value);
                case "text":
                    return page.getByText(value);
                case "title":
                    return page.getByTitle(value);
                default:
                    throw new CustomException("Locator Strategy misspelled or does not exist");
            }
        } else {
            return page.locator(locString);
        }
    }

    /**
     * Method to find Element by given Locator from a FrameLocator
     *
     * @param fieldName - Field Name having locator stored in
     * locators.properties
     * @param frameLocator - FrameLocator on which you are looking for Element
     * @return - Locator Object having Browser Element
     */
    public static Locator getElement(String fieldName, FrameLocator frameLocator) {
        Locator locator = null;
        int counter = 0;
        String locString = Constant.locatorsMap.get(fieldName.replaceAll(" ", "_").toLowerCase());
        if (locString.contains("||")) {
            String[] locators = locString.split("\\|\\|");
            for (int i = 0; i < locators.length; i++) {
                //System.out.println("\nChecking locator " + ++counter);
                try {
                    locator = getTypeOfElement(locators[i], frameLocator);
                } catch (Exception e) {
                    if (i == locators.length - 1) {
                        throw new CustomException("Element not found correct your all locators");
                    }
                    continue;
                }
                break;
            }
        } else {
            locator = getTypeOfElement(locString, frameLocator);
        }
        return locator;
    }

    /**
     * Method to find Element based on different Locator from a FrameLocator
     *
     * @param locString - Locator Value
     * @param frameLocator - FrameLocator on which you are looking for Element
     * @return - Locator Object having Browser Element
     */
    public static Locator getTypeOfElement(String locString, FrameLocator frameLocator) {
        if (locString.contains("*")) {
            String type = locString.split("*")[0].toLowerCase();
            String value = locString.split("*")[1];
            switch (type) {
                case "alttext":
                    return frameLocator.getByAltText(value);
                case "label":
                    return frameLocator.getByLabel(value);
                case "placeholder":
                    return frameLocator.getByPlaceholder(value);
                case "testid":
                    return frameLocator.getByTestId(value);
                case "text":
                    return frameLocator.getByText(value);
                case "title":
                    return frameLocator.getByTitle(value);
                default:
                    throw new CustomException("Locator Strategy misspelled or does not exist");
            }
        } else {
            return frameLocator.locator(locString);
        }
    }

    /**
     * Method to find Element by given Locator from a Locator
     *
     * @param fieldName - Field Name having locator stored in
     * locators.properties
     * @param loc - Locator on which you are looking for Element
     * @return - Locator Object having Browser Element
     */
    public static Locator getElement(String fieldName, Locator loc) {
        Locator locator = null;
        int counter = 0;
        String locString = Constant.locatorsMap.get(fieldName.replaceAll(" ", "_").toLowerCase());
        if (locString.contains("||")) {
            String[] locators = locString.split("\\|\\|");
            for (int i = 0; i < locators.length; i++) {
                //System.out.println("\nChecking locator " + ++counter);
                try {
                    locator = getTypeOfElement(locators[i], loc);
                } catch (Exception e) {
                    if (i == locators.length - 1) {
                        throw new CustomException("Element not found correct your all locators");
                    }
                    continue;
                }
                break;
            }
        } else {
            locator = getTypeOfElement(locString, loc);
        }
        return locator;
    }

    /**
     * Method to find Element based on different Locator from a Locator
     *
     * @param locString - Locator Value
     * @param loc - Locator on which you are looking for Element
     * @return - Locator Object having Browser Element
     */
    public static Locator getTypeOfElement(String locString, Locator locator) {
        if (locString.contains("*")) {
            String type = locString.split("*")[0].toLowerCase();
            String value = locString.split("*")[1];
            switch (type) {
                case "alttext":
                    return locator.getByAltText(value);
                case "label":
                    return locator.getByLabel(value);
                case "placeholder":
                    return locator.getByPlaceholder(value);
                case "testid":
                    return locator.getByTestId(value);
                case "text":
                    return locator.getByText(value);
                case "title":
                    return locator.getByTitle(value);
                default:
                    throw new CustomException("Locator Strategy misspelled or does not exist");
            }
        } else {
            return locator.locator(locString);
        }
    }

    /**
     * Method to Select Option from Dropdown by Value
     *
     * @param valueToSelect - Dropdown Option Value
     * @param locator - Dropdown Locator
     */
    public static void selectByValueFromDropdown(String valueToSelect, Locator locator) {
        try {
            SelectOption options = new SelectOption();
            options.value = valueToSelect;
            locator.selectOption(options);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Selects an option from a dropdown based on the visible text (label) of
     * the option.
     *
     * @param textToSelect The visible text of the option to be selected.
     * @param locator The Playwright Locator that identifies the dropdown
     * element.
     * @throws CustomException if the selection process fails.
     */
    public static void selectByVisibleTextFromDropdown(String textToSelect, Locator locator) {
        try {
            // Create a new SelectOption object, which allows selecting an option from the
            // dropdown.
            PlaywrightUtils.click(locator);
            PlaywrightUtils.waitForSec();
            SelectOption options = new SelectOption();

            // Set the label (visible text) of the option to select. This will match the
            // text shown to the user.
            options.setLabel(textToSelect);

            // Use the locator to select the option from the dropdown based on the visible
            // text (label).
            locator.selectOption(options);
        } catch (Exception e) {
            // If any exception occurs during the selection process, throw a CustomException
            // with the error message.
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Checks if the first word of the string contains any numbers.
     *
     * @param input the string to check
     * @return true if the first word contains numbers, false otherwise
     */
    public static boolean doesFirstWordContainNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        // Extract the first word by trimming any leading/trailing spaces and splitting
        // by space
        String[] words = input.trim().split("\\s+");
        String firstWord = words[0];

        // Check if the first word contains a number
        return firstWord.matches(".*\\d.*");
    }

    // Method to extract the first word of a string
    public static String extractFirstWord(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        String[] words = input.trim().split("\\s+");
        return words[0]; // Return the first word
    }

    public static Integer extractFirstWordAndConvertToInt(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; // Return null if the input is invalid
        }

        String[] words = input.trim().split("\\s+");
        String firstWord = words[0]; // Extract the first word

        try {
            return Integer.parseInt(firstWord); // Try converting the first word to an integer
        } catch (NumberFormatException e) {
            // If conversion fails, return null or handle as needed
            //System.out.println("First word is not a valid integer.");
            return null;
        }
    }

    /**
     * Method to Select Option from Dropdown by Index
     *
     * @param index - Dropdown Index
     * @param locator - Dropdown Locator
     */
    public static void selectByIndexFromDropdown(int index, Locator locator) {
        try {
            SelectOption options = new SelectOption();
            options.index = Integer.valueOf(index);
            locator.selectOption(options);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Select Option from Dropdown by Label Value
     *
     * @param labelToSelect - Dropdown Option Label
     * @param locator - Dropdown Locator
     */
    public static void selectByLabelFromDropdown(String labelToSelect, Locator locator) {
        try {
            SelectOption options = new SelectOption();
            options.label = labelToSelect;
            locator.selectOption(options);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * @author - QA implemented code Method to select option from dropdown
     * by randomly form available options
     * @param locator
     * @param fieldName
     */
    public static void selectByRandomAvaliableOptionsFromDropdown(Locator locator, Locator selector, String fieldName) {
        int dropdown_random_avaliable_option = CommonUtils.generateRandomNumberfromGivenNumber(locator.count());
        //System.out.println("Random dropdown value" + dropdown_random_avaliable_option);
        int index_of_dropdown_avaliable_option = dropdown_random_avaliable_option - 1;
        PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, PlaywrightManager.getPage()));
        SelectOption options = new SelectOption();
        options.index = Integer.valueOf(index_of_dropdown_avaliable_option);
        selector.selectOption(options);
        Constant.SelectedDropdownValue = (String) selector.evaluate("el => el.options[el.selectedIndex].text");
    }

    /**
     * @author - QA implemented code Method to select option from dropdown
     * by randomly form available options
     * @param locator
     * @param fieldName
     */
    public static void selectByRandomAvaliableOptionsFromDropdownofExpDetails(Locator locator, Locator selector, String fieldName) {
        int dropdown_random_avaliable_option = CommonUtils.generateRandomNumberfromGivenNumber(locator.count());
        //System.out.println("Random dropdown value" + dropdown_random_avaliable_option);
        if (dropdown_random_avaliable_option == 1 || dropdown_random_avaliable_option == 2) {
            dropdown_random_avaliable_option = dropdown_random_avaliable_option + 2;
        }
        int index_of_dropdown_avaliable_option = dropdown_random_avaliable_option - 1;
        PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, PlaywrightManager.getPage()));
        SelectOption options = new SelectOption();
        options.index = Integer.valueOf(index_of_dropdown_avaliable_option);
        selector.selectOption(options);
        Constant.SelectedDropdownValue = (String) selector.evaluate("el => el.options[el.selectedIndex].text");
    }

    /**
     * @author - QA implemented code Method to select option from dropdown
     * by randomly form available options
     * @param locator
     * @param fieldName
     */
    public static void selectByRandomAvaliableOptionsFromDropdownofcreditcard(Locator locator, Locator selector, String fieldName) {
        int dropdown_random_avaliable_option = CommonUtils.generateRandomNumberfromGivenNumber(locator.count() - 1);
        //System.out.println("Random dropdown value " + dropdown_random_avaliable_option);
        if (dropdown_random_avaliable_option == 1) {
            dropdown_random_avaliable_option = dropdown_random_avaliable_option + 1;
        }
        int index_of_dropdown_avaliable_option = dropdown_random_avaliable_option - 1;
        PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, PlaywrightManager.getPage()));
        PlaywrightUtils.waitForSec();
        SelectOption options = new SelectOption();
        options.index = Integer.valueOf(index_of_dropdown_avaliable_option);
        selector.selectOption(options);
        Constant.SelectedDropdownValue = (String) selector.evaluate("el => el.options[el.selectedIndex].text");
    }

    /**
     * @author - QA implemented code Method to select option from dropdown
     * by randomly form available options
     * @param locator
     * @param fieldName
     */
    public static void select_Dropdown_Attributes(Locator locator, Locator selector, String fieldName) {

        int dropdown_random_avaliable_option = CommonUtils.generateRandomNumberfromGivenNumber(locator.count());
        //System.out.println("Random dropdown value " + dropdown_random_avaliable_option);
        if (dropdown_random_avaliable_option == 1) {
            dropdown_random_avaliable_option = dropdown_random_avaliable_option + 1;
        }
        int index_of_dropdown_avaliable_option = dropdown_random_avaliable_option - 1;
        PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, PlaywrightManager.getPage()));
        PlaywrightUtils.waitForSec();
        SelectOption options = new SelectOption();
        options.index = Integer.valueOf(index_of_dropdown_avaliable_option);
        selector.selectOption(options);
        Constant.SelectedDropdownValue = (String) selector.evaluate("el => el.options[el.selectedIndex].text");
    }

    /**
     * @author - QA implemented code Method to select option from dropdown
     * by randomly form available options
     * @param locator
     * @param fieldName
     */
    public static void selectByRandomAvaliableOptionsFromDropdownwithnegativeOptionsAvaliable(Locator locator, Locator selector, String fieldName) {

        int dropdown_random_avaliable_option = CommonUtils.generateRandomNumberFromgreaterThanfive(locator.count());
        //System.out.println("Random dropdown value" + dropdown_random_avaliable_option);
        int index_of_dropdown_avaliable_option = dropdown_random_avaliable_option - 1;
        PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, PlaywrightManager.getPage()));
        SelectOption options = new SelectOption();
        options.index = Integer.valueOf(index_of_dropdown_avaliable_option);
        selector.selectOption(options);
        Constant.SelectedDropdownValue = (String) selector.evaluate("el => el.options[el.selectedIndex].text");
    }

    /**
     * @author - QA implemented code Method to select option from dropdown
     * by randomly form available options
     * @param locator
     * @param fieldName
     */
    public static void selectByRandomAvaliableOptionsFromDropdownwithOneSelectOption(Locator locator, Locator selector,
            String fieldName) {
        int dropdown_random_avaliable_option = CommonUtils.generateRandomNumberfromGivenNumber(locator.count());
        int index_of_dropdown_avaliable_option = dropdown_random_avaliable_option - 1;
        PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, PlaywrightManager.getPage()));
        SelectOption options = new SelectOption();
        options.index = Integer.valueOf(index_of_dropdown_avaliable_option);
        selector.selectOption(options);
        // Print the selected option's value
        String selectedOptionValue = (String) selector.evaluate("el => el.options[el.selectedIndex].value");
        //System.out.println("Selected option value: " + selectedOptionValue.trim());
    }

    /**
     * Method to Click on Element
     *
     * @param locator - Element Locator
     */
    public static void click(Locator locator) {
        try {
            // ClickOptions options = new ClickOptions();
            // options.button = MouseButton.LEFT;
            //waitForAnElement(locator);
            highlightElement(PlaywrightManager.getPage(), locator);
            //PlaywrightUtils.waitForSec();
            locator.click();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public static void Click(Locator locator) {
        PlaywrightUtils.waitForSec();
        locator.click();
    }

    public static void forceClick(Locator locator) {

        try {
            // ClickOptions options = new ClickOptions();
            // options.button = MouseButton.LEFT;
            highlightElement(PlaywrightManager.getPage(), locator);
            PlaywrightUtils.waitForSec();
            locator.click(new Locator.ClickOptions().setForce(true));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * Method to Double Click on Element
     *
     * @param locator - Element Locator
     */
    public static void doubleClick(Locator locator) {
        try {
            DblclickOptions options = new DblclickOptions();
            options.button = MouseButton.LEFT;
            locator.dblclick(options);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Scroll Vertically
     *
     * @param page - Page on which Scroll needs to be performed
     */
    public static void scrollVertically(Page page) {
        try {
            page.mouse().wheel(0, 250);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to scroll vertically by give co-ordinates
     *
     * @author ETG-QA implemented code
     * @param page
     * @param scrollviewNumber
     */
    public static void scrollVertically(Page page, int scrollviewNumber) {
        try {
            page.mouse().wheel(0, scrollviewNumber);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Scroll Horizontally
     *
     * @param page - Page on which Scroll needs to be performed
     */
    public static void scrollHorizontally(Page page) {
        try {
            page.mouse().wheel(250, 0);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Scroll Element into View if needed
     *
     * @param locator - Element Locator which need to be scrolled
     */
    public static void scrollToElement(Locator locator) {
        try {
            locator.scrollIntoViewIfNeeded();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Scroll Element into View if needed
     *
     * @param locator - Element Locator which need to be scrolled
     * @return - locator
     */
    public static Locator scrollToElementandreturn(Locator locator) {
        try {
            locator.scrollIntoViewIfNeeded();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return locator;
    }

    /**
     * Method to Scroll until Element displayed
     *
     * @param locator - Element Locator which needs to ne scrolled
     * @param lastElementOfPage - Last Element Locator of the Page
     */
    public static void scrollUntilElementDisplayed(Locator locator, Locator lastElementOfPage, Page page) {
        try {
            WaitForOptions options = new WaitForOptions();
            options.timeout = (double) 5000;
            options.state = WaitForSelectorState.VISIBLE;
            for (int i = 0; i < 10; i++) {
                if (lastElementOfPage.isVisible()) {
                    break;
                }
                try {
                    locator.waitFor(options);
                } catch (Exception e) {
                    scrollVertically(page);
                    continue;
                }
            }
            locator.scrollIntoViewIfNeeded();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Mouse Click on Element using Coordinates
     *
     * @param locator - Element Locator
     */
    public static void clickUsingCoordinates(Locator locator) {
        try {
            BoundingBox box = locator.boundingBox();
            PlaywrightManager.getPage().mouse().click(box.x + box.width / 2, box.y + box.height / 2);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Mouse Hover on Element
     *
     * @param locator - Element Locator
     */
    public static void hover(Locator locator) {
        try {
            highlightElement(PlaywrightManager.getPage(), locator);
            locator.hover();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to get FrameLocator from Page
     *
     * @param frameName - Frame Selector
     * @param page - Page containing Frame
     * @return - FrameLocator to Interact with Inside Elements
     */
    public static FrameLocator getFrame(String frameName, Page page) {
        try {
            return page.frameLocator(Constant.locatorsMap.get(frameName.replaceAll(" ", "_").toLowerCase()));
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to get Child FrameLocator from Parent FrameLocator
     *
     * @param frameName - Child Frame Name/Selector
     * @param frameLocator - Parent FrameLocator
     * @return - FrameLocator to Interact with Inside Elements in Child Frame
     */
    public static FrameLocator getFrame(String frameName, FrameLocator frameLocator) {
        try {
            return frameLocator.frameLocator(frameName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Accept Alert
     *
     * @param page - Page on which Alert occurs
     */
    public static void alertAccept(Page page) {
        try {
            page.onDialog(dialog -> {
                dialog.accept();
            });
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Check Checkbox
     *
     * @param locator - Checkbox Locator
     */
    public static void checkCheckbox(Locator locator) {
        try {
            if (!locator.isChecked()) {
                locator.check();
            } else {
                throw new CustomException(locator.toString() + " Checkbox already checked");
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Uncheck Checkbox
     *
     * @param locator - Checkbox Locator
     */
    public static void uncheckCheckbox(Locator locator) {
        try {
            if (locator.isChecked()) {
                locator.uncheck();
            } else {
                throw new CustomException(locator.toString() + " Checkbox already unchecked");
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Wait for URL to occur on Page
     *
     * @param url - URL/Some Part of URL/URL pattern
     * @param page - Page on which URL should occur
     */
    public static void waitForURL(String url, Page page) {
        try {
            page.waitForURL(url);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Refresh Browser
     *
     * @param page - Page on which Refresh needs to performed
     */
    public static void refreshBrowser(Page page) {
        page.reload();
        PlaywrightUtils.waitForPageToLoad(page);
    }

    /**
     * Method to Set Value on Element(Input Fields)
     *
     * @param value - Value
     * @param locator - Element Locator
     */
    public static void setValue(String value, Locator locator) {
        try {
            //highlightElement(PlaywrightManager.getPage(), locator);
            locator.fill(value);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Clear then Set Value on Element(Input Fields)
     *
     * @param value - Value
     * @param locator - Element Locator
     */
    public static void clearAndSetValue(String value, Locator locator) {
        try {
            locator.clear();
            locator.fill(value);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Set Value on Element(Input Fields) using Keyboard
     *
     * @param value - Value
     * @param locator - Element Locator
     */
    public static void setValueUsingKeyboard(String value, Locator locator) {
        try {
            locator.clear();
            highlightElement(PlaywrightManager.getPage(), locator);
            locator.type(value);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * @author - Etg - QA Method to set Value on Element (Input Fields) using
     * Keyboard and Press enter
     * @param value - Value
     * @param locator - Element Locator
     */
    public static void enters_value_and_presses_enter(String value, Locator locator) {
        try {
            highlightElement(PlaywrightManager.getPage(), locator);
            locator.fill(value);
            PlaywrightUtils.waitForMoreSec(1);
            locator.press("Enter");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Clear then Set Value on Element(Input Fields) using Keyboard
     *
     * @param value - Value
     * @param locator - Element Locator
     */
    public static void clearAndSetValueUsingKeyboard(String value, Locator locator) {
        try {
            highlightElement(PlaywrightManager.getPage(), locator);
            locator.clear();
            locator.type(value);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Focus on Element
     *
     * @param locator - Element Locator
     */
    public static void focusOnElement(Locator locator) {
        try {
            FocusOptions options = new FocusOptions();
            options.timeout = (double) 120000;
            locator.focus();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Blur an Element
     *
     * @param locator = Element Locator
     */
    public static void blurElement(Locator locator) {
        try {
            locator.blur();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to wait for Element is Visible
     *
     * @param locator - Element Locator
     */
    public static void waitForAnElement(Locator locator) {
        try {
            WaitForOptions options = new WaitForOptions();
            options.timeout = (double) 60000;
            options.state = WaitForSelectorState.VISIBLE;
            locator.waitFor(options);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /*
	 * public static Locator waitForAnElement(Locator locator) { try {
	 * WaitForOptions options = new WaitForOptions(); options.setTimeout(240000); //
	 * setting timeout to 240000 milliseconds
	 * options.setState(WaitForSelectorState.VISIBLE); // setting state to VISIBLE
	 * locator.waitFor(options); } catch (Exception e) { throw new
	 * CustomException(e.getMessage()); } return locator; }
	 * 
     */
    /**
     * Method to verify that element is enabled or not
     *
     * @param locator - Element locator
     */
    public static boolean elementIsEnabled(Locator locator) {
        if (locator.isEnabled()) {
            //System.out.println("Element is enabled");
            return true;
        } else {
            //System.out.println("Element is disabled");
            return false;
        }
    }

    /**
     * Method to remove the doller symbol
     *
     * @param value
     * @return
     */
    public static double removedollersymbol(Locator locaror) {
        String stringwithdoller = locaror.textContent();
        // Remove any whitespace
        String cleanAmount = stringwithdoller.trim();
        // Remove the dollar sign
        cleanAmount = cleanAmount.replaceAll("[^\\d.]+", "");
        // Parse the string as a double (since it contains decimal points)
        double amount = Double.parseDouble(cleanAmount);
        // //System.out.println(amount);
        return amount;
    }

    /**
     * Method to Get Text from Element
     *
     * @param locator - Element Locator
     * @return = Locator Text
     */
    public static int getTextAsInt(Locator locator) {
        try {
            String text = locator.textContent();
            // Assuming the text content is a valid integer string
            // //System.out.println("count of products "+text);
            //System.out.println("mini cart count " + text);
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new CustomException("Failed to convert text to integer: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to wait for Element is Visible
     *
     * @param locator - Element Locator
     */
    public static void waitForAnElementToDisappear(Locator locator) {
        try {
            WaitForOptions options = new WaitForOptions();
            options.timeout = (double) 300000;
            options.state = WaitForSelectorState.HIDDEN;
            locator.waitFor(options);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to wait for page to load
     *
     * @param page - Page requires wait
     */
    public static void waitForPageToLoad(Page page) {
        try {
            page.waitForLoadState();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Switch to Tab by given URL
     *
     * @param fieldName - Field Name for Click operation
     * @param URL - Part of URL/URL of the Tab you need to switch
     * @param page - Page on which Click will be performed
     * @return - New Tab Page
     */
    public static Page clickThenSwitchToTab(String fieldName, String URL, Page page) {
        Page newPage = Constant.BROWSERCONTEXT.waitForPage(() -> {
            PlaywrightUtils.click(PlaywrightUtils.getElement(fieldName, page));
        });
        newPage.waitForLoadState();
        return newPage;
    }

    /**
     * Method to wait for more time(Integer value multiplied by 3 seconds)
     *
     * @param multiplier - Integer value
     */
    public static void waitForMoreSec(int multiplier) {
        try {
            TimeUnit.MILLISECONDS.sleep(multiplier * 3000);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method for Wait
     */
    public static void waitForSec() {
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method for Wait
     */
    public static void waitForOneSec() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Get Text from Element
     *
     * @param locator - Element Locator
     * @return = Locator Text
     */
    public static String getText(Locator locator) {
        try {
            return locator.textContent();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Get Text from Element
     *
     * @param locator - Element Locator
     * @return = Locator Text
     */
    public static int getTextint(Locator locator) {
        try {
            return Integer.parseInt(locator.textContent());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Get All Text from Elements
     *
     * @param locator - Element Locator
     * @return List<String> - List of text contents of located elements
     */
    public static List<String> getAllText(Locator locator) {
        try {
            // Return all text contents from the located elements as a List of strings
            return locator.allTextContents();
        } catch (Exception e) {
            // Throw a custom exception in case of an error
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Get All Text from Elements
     *
     * @param locator - Element Locator
     * @return List<String> - List of text contents of located elements
     */
    public static List<Double> getAllTextAsDoubles(Locator locator) {
        try {
            // Retrieve all text contents from the located elements
            List<String> textContents = locator.allTextContents();

            // Convert the list of text contents to a list of Double values
            return textContents.stream()
                    .map(text -> {
                        try {
                            // Remove dollar signs and any non-numeric characters before parsing
                            String cleanedText = text.replaceAll("[\\$,]", "").trim();
                            return Double.parseDouble(cleanedText);
                        } catch (NumberFormatException e) {
                            // Handle the case where the text cannot be parsed as a Double
                            throw new CustomException("Unable to parse text to Double: " + text);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Throw a custom exception in case of an error
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Press Keyboard Keys
     *
     * @param keyName - Name of the Key
     * @param page - Page on which Key should be pressed
     */
    public static void keyPress(String keyName, Page page) {
        try {
            page.keyboard().press(keyName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to get Current Url from Browser Page
     *
     * @param page - Page from which URL is required
     * @return - Page URL
     */
    public static String getCurrentURL(Page page) {
        try {
            return page.url();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Navigate to URL
     *
     * @param url - URL
     * @param page - Page in which you need to open new URL
     */
    public static void navigateToURL(String url, Page page) {
        try {
            page.navigate(url);
            page.waitForLoadState();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method to Switch to Tab by given URL
     *
     * @param URL - Part of URL/URL of the Tab you need to switch
     * @return - New Tab Page
     */
    public static Page switchToNewTab(String URL) {
        Page newPage = Constant.BROWSERCONTEXT.newPage();
        newPage.navigate(URL);
        return newPage;
    }

    /**
     * @author QA Method to count no of elements present on the page
     * @params - locator
     * @return - true or false
     */
    public static boolean no_of_elements_present_on_page(Locator locator) {
        PlaywrightUtils.waitForMoreSec(1);
        if (locator.count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author QA Method to count no of elements present on the page
     * @params - locator
     * @return - true or false
     */
    public static int no_of_elements_present_on_the_page(Locator locator) {
        PlaywrightUtils.waitForMoreSec(1);

        return locator.count();

    }

    /**
     * Method to Scroll Element into View in the middle of the viewport
     *
     * @param locator - Element Locator which needs to be scrolled
     */
    public static void scrollToElementInMiddle(Locator locator) {
        try {
            locator.evaluate("element => element.scrollIntoView({ behavior: 'smooth', block: 'center' })");
            waitForMoreSec(1);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * @author QA Method to count no of elements present on the page
     * @params - locator
     * @return - int count
     */
    public static int no_of_elements_present_and_displayed_on_a_page_(Locator locator) {
        // PlaywrightUtils.waitForAnElement(locator);
        PlaywrightUtils.waitForMoreSec(1);
        int no_of_elements_displayed = 0;
        for (int i = 0; i < locator.count(); i++) {
            if (locator.nth(i).isVisible()) {
                no_of_elements_displayed++;
                ////System.out.println("diplayed "+no_of_elements_displayed);
            }
        }
        return no_of_elements_displayed;

    }

    /**
     * Method to close all other tabs except given tab URL
     *
     * @param URL - Part of URL/URL of the Tab you need to switch
     * @return - New Tab Page
     */
    public static void closeAllTabsExceptGivenURLTab(String URL) {
        List<Page> pages = Constant.BROWSERCONTEXT.pages();
        for (Page page : pages) {
            if (!page.url().contains(URL)) {
                PlaywrightUtils.waitForSec();
                page.close();
            }
        }
    }

    /**
     * @author ETG - QA Method to highlight the element which is interacted
     * @param page
     * @param locator
     */
    public static void highlightElement(Page page, Locator locator) {
        try {
            // PlaywrightUtils.waitForSec();
            // Get the element handle from the locator
            ElementHandle elementHandle = locator.elementHandle();
            if (elementHandle != null) {
                // Use JavaScript to highlight the element
                page.evaluate("element => element.style.border='3px solid yellow'", elementHandle);
            } else {
                throw new CustomException("Element handle is null");
            }
        } catch (Exception e) {
            throw new CustomException("Failed to highlight element: " + e.getMessage());
        }
    }

    public static void popups(Page page) {
        page.onDialog(dialog -> {
            //System.out.println("Dialog message: " + dialog.message());
            dialog.accept(); // Use dialog.dismiss() to close without accepting.
        });
    }

    /**
     * Method to take screenshot
     */
    public static void addScreenshotToReport() {
        String path = System.getProperty("user.dir") + "/target/screenshots/" + System.currentTimeMillis() + ".png";
        PlaywrightManager.getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
        ExtentTest test = ExtentCucumberAdapter.getCurrentStep();
        test.pass("", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }

    /**
     * @author ETG - QA Method to take a screenshot on the new page
     */
    public static void addScreenshotToReportNewPage() {
        Page newPage = PlaywrightManager.getPage().waitForPopup(() -> {
            PlaywrightUtils.click(CommonUtils.social_media_random_footer_link(PlaywrightManager.getPage()));
            PlaywrightUtils.waitForMoreSec(1);
        });
        Constant.NEWPAGE = newPage;
        String path = System.getProperty("user.dir") + "/target//screenshots/" + System.currentTimeMillis() + ".png";
        Constant.NEWPAGE.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
        ExtentTest test = ExtentCucumberAdapter.getCurrentStep();
        test.pass("Screenshot taken successfully", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Constant.NEWPAGE.close();
    }

    /**
     * @author ETG - QA Method to take a screenshot on the new page
     * @param element - name of the locator
     */
    public static void addScreenshotToReportNewPage(String element) {
        Page newPage = PlaywrightManager.getPage().waitForPopup(() -> {
            PlaywrightUtils.click(CommonUtils.getSocialMediaFooterLinkLocators(element, PlaywrightManager.getPage()));
            PlaywrightUtils.waitForMoreSec(1);
        });
        Constant.NEWPAGE = newPage;
        String path = System.getProperty("user.dir") + "/target//screenshots/" + System.currentTimeMillis() + ".png";
        Constant.NEWPAGE.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
        ExtentTest test = ExtentCucumberAdapter.getCurrentStep();
        test.pass("Screenshot taken successfully", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Constant.NEWPAGE.close();
    }

    /**
     * @author ETG - QA Method to take a screenshot on the new page
     * @param element - name of the locator
     */
    public static void VerifyNewPage(String element) {
        Page newPage = PlaywrightManager.getPage().waitForPopup(() -> {
            PlaywrightUtils.click(getElement(element, PlaywrightManager.getPage()));
            PlaywrightUtils.waitForMoreSec(1);
        });
        Constant.NEWPAGE = newPage;
        String path = System.getProperty("user.dir") + "/target//screenshots/" + System.currentTimeMillis() + ".png";
        Constant.NEWPAGE.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
        ExtentTest test = ExtentCucumberAdapter.getCurrentStep();
        test.pass("Screenshot taken successfully", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Constant.NEWPAGE.close();
    }

    /**
     * @author EtG - QA implemented code
     * @param value - google suggestion value
     * @param locator - Element Locator
     * @param page - Page
     */
    public static void setValueUsingGoogleSuggestions(String value, Locator locator, Page page) {
        try {
            locator.clear();
            page.waitForTimeout(2000);
            locator.fill(value);
            page.waitForTimeout(3000);
            locator.press("ArrowDown");
            page.waitForTimeout(2000);
            locator.press("Enter");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * @author EtG - QA implemented code
     * @param value - google suggestion value
     * @param locator - Element Locator
     * @param page - Page
     */
    public static void setValueUsingGoogleSuggestionswithoutEnter(String value, Locator locator, Page page) {
        try {
            locator.clear();
            page.waitForTimeout(2000);
            locator.fill(value);
            page.waitForTimeout(3000);
            locator.press("ArrowDown");
            page.waitForTimeout(2000);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    // public static boolean isSortedAscending(List<String> allText, String string)
    // {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'isSortedAscending'");
    // }
}
