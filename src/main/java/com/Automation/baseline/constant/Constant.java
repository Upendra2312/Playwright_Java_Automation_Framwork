package com.Automation.baseline.constant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.Scenario;

/**
 * @Author: QA
 */
public class Constant {

    private Constant() {
    }

    /**
     * Constant having Playwright Reference
     */
    public static Playwright PLAYWRIGHT;

    /**
     * Constant having BrowserContext Reference
     */
    public static BrowserContext BROWSERCONTEXT;

    /**
     * Constant having Page Reference
     */
    public static Page PAGE;

    /**
     * Constant having New Page Reference
     */
    public static Page NEWPAGE;

    /**
     * Constant having FrameLocator Reference
     */
    public static FrameLocator FRAME;

    /**
     * Constant having configuration properties file path
     */
    public static String ConfigPropertiesFilePath = "src/test/resources/TestData/config.properties";

    /**
     * Constant having TestData excel file path
     */
    public static String TestDataExcelFilePath = "src/test/resources/TestData/TestData.xlsx";

    /**
     * Constant having configuration properties json file path
     */
    public static String JsonData = "src/test/resources/TestData/Data.json";

    /**
     * Constant having Locators properties file path
     */
    public static String LocatorsJSONFilePath = "src/test/resources/Locators/locators.properties";

    /**
     * Constant Map having Locators keys and values
     */
    public static HashMap<String, String> locatorsMap = new HashMap<>();
    /**
     * Constant for storing the Base URL after trimming the RecordID from the
     * current URL.
     */
    public static String baseURL;

    /**
     * Constant for storing the value of the selected shipping method from the
     * dropdown.
     */
    public static String SelectedDropdownValue;

    /**
     * Stores the subtotal amount displayed on the View Cart page.
     */
    public static double ViewCartPageSubTotal;

    /**
     * Stores the product discount amount displayed on the View Cart page.
     */
    public static double ViewCartPageProductDiscount;

    /**
     * Stores the shipping cost amount displayed on the View Cart page.
     */
    public static double ViewCartPageShippingCost;

    /**
     * Stores the shipping discount amount displayed on the View Cart page.
     */
    public static double ViewCartPageShippingDiscount;

    /**
     * Stores the sales tax amount displayed on the View Cart page.
     */
    public static double ViewCartPageSalesTax;

    /**
     * Stores the order discount amount displayed on the View Cart page.
     */
    public static double ViewCartPageOrderDiscount;

    /**
     * Stores the estimated total amount displayed on the View Cart page.
     */
    public static double ViewCartPageEstimatedTotal;

    /**
     * Stores the subtotal amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageSubTotal;

    /**
     * Stores the product discount amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageProductDiscount;

    /**
     * Stores the shipping cost amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageShippingCost;

    /**
     * Stores the shipping discount amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageShippingDiscount;

    /**
     * Stores the sales tax amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageSalesTax;

    /**
     * Stores the order discount amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageOrderDiscount;

    /**
     * Stores the estimated total amount displayed on the View Cart page.
     */
    public static double CheckoutBeginPageEstimatedTotal;

    /**
     * Stores the subtotal amount displayed on the Shipping page.
     */
    public static double ShippingPageSubTotal;

    /**
     * Stores the product discount amount displayed on the Shipping page.
     */
    public static double ShippingPageProductDiscount;

    /**
     * Stores the shipping cost amount displayed on the Shipping page.
     */
    public static double ShippingPageShippingCost;

    /**
     * Stores the shipping discount amount displayed on the Shipping page.
     */
    public static double ShippingPageShippingDiscount;

    /**
     * Stores the sales tax amount displayed on the Shipping page.
     */
    public static double ShippingPageSalesTax;

    /**
     * Stores the order discount amount displayed on the Shipping page.
     */
    public static double ShippingPageOrderDiscount;

    /**
     * Stores the estimated total amount displayed on the Shipping page.
     */
    public static double ShippingPageEstimatedTotal;

    /**
     * Stores the subtotal amount displayed on the Shipping page.
     */
    public static double PaymentPageSubTotal;

    /**
     * Stores the product discount amount displayed on the Payment page.
     */
    public static double PaymentPageProductDiscount;

    /**
     * Stores the Payment cost amount displayed on the Payment page.
     */
    public static double PaymentPageShippingCost;

    /**
     * Stores the Payment discount amount displayed on the Payment page.
     */
    public static double PaymentPageShippingDiscount;

    /**
     * Stores the sales tax amount displayed on the Payment page.
     */
    public static double PaymentPageSalesTax;

    /**
     * Stores the order discount amount displayed on the Payment page.
     */
    public static double PaymentPageOrderDiscount;

    /**
     * Stores the estimated total amount displayed on the Payment page.
     */
    public static double PaymentPageEstimatedTotal;

    /**
     * Stores the subtotal amount displayed on the Shipping page.
     */
    public static double ReviewOrderPageSubTotal;

    /**
     * Stores the product discount amount displayed on the ReviewOrder page.
     */
    public static double ReviewOrderPageProductDiscount;

    /**
     * Stores the Payment cost amount displayed on the Payment page.
     */
    public static double ReviewOrderPageShippingCost;

    /**
     * Stores the Payment discount amount displayed on the Payment page.
     */
    public static double ReviewOrderPageShippingDiscount;

    /**
     * Stores the sales tax amount displayed on the Payment page.
     */
    public static double ReviewOrderPageSalesTax;

    /**
     * Stores the order discount amount displayed on the Payment page.
     */
    public static double ReviewOrderPageOrderDiscount;

    /**
     * Stores the estimated total amount displayed on the Payment page.
     */
    public static double ReviewOrderPageEstimatedTotal;

    /**
     * Stores the subtotal amount displayed on the Order confirmation page.
     */
    public static double OrderConfirmationPageSubTotal;

    /**
     * Stores the product discount amount displayed on the Order confirmation
     * page.
     */
    public static double OrderConfirmationPageProductDiscount;

    /**
     * Stores the Payment cost amount displayed on the Order confirmation page.
     */
    public static double OrderConfirmationPageShippingCost;

    /**
     * Stores the Payment discount amount displayed on the Order confirmation
     * page.
     */
    public static double OrderConfirmationPageShippingDiscount;

    /**
     * Stores the sales tax amount displayed on the Order confirmation page.
     */
    public static double OrderConfirmationPageSalesTax;

    /**
     * Stores the order discount amount displayed on the Order confirmation
     * page.
     */
    public static double OrderConfirmationPageOrderDiscount;

    /**
     * Stores the estimated total amount displayed on the Order confirmation
     * page.
     */
    public static double OrderConfirmationPageOrderTotal;

    /**
     * Stores the Order Number displayed on the Order confirmation page.
     */
    public static String OrderConfirmationOrderNumber;

    /**
     * Stores the Order Date displayed on the Order confirmation page.
     */
    public static String OrderConfirmationOrderDate;

    /**
     * Stores the Shipping Address displayed on the Order confirmation page.
     */
    public static String OrderConfirmationShippingAddress;

    /**
     * Stores the Billing Address displayed on the Order confirmation page.
     */
    public static String OrderConfirmationBillingAddress;

    /**
     * Stores the Payment Details displayed on the Order confirmation page.
     */
    public static String OrderConfirmationPaymentDetails;

    /**
     * Stores the previous email address used during checkout or account
     * creation.
     */
    public static String previousEmail;

    /**
     * Stores the current email address being used during checkout or account
     * creation.
     */
    public static String PresentEmail;

    /**
     * Stores the previous shipping address used during checkout.
     */
    public static String PreviousShippingAddress;

    /**
     * Stores the previous shipping address used during checkout.
     */
    public static String PaymentPageShippingAddress;

    /**
     * Stores the previous shipping address used during checkout.
     */
    public static String ReviewOrderPageShippingAddress;

    /**
     * Stores the previous shipping address used during checkout.
     */
    public static String OrderConformationPageShippingAddress;

    /**
     * Stores the previous billing address used during checkout.
     */
    public static String PreviousBillingAddress;

    /**
     * Stores the previous billing address used during checkout.
     */
    public static String ReviewOrderPageBillingAddress;

    /**
     * Stores the previous billing address used during checkout.
     */
    public static String OrderConformationPageBillingAddress;

    /**
     * Stores the present billing address on review order page after editing
     * used during checkout.
     */
    public static String PresentBillingAddress;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PresentShippingAddress;

    /**
     * Stores the previous shipping address used during checkout.
     */
    public static String PreviousPaymentDetails;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PresentPaymentDetails;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PreviousMyAccountPageFullName;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PreviousMyAccountPageEmailAddress;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PreviousMyAccountPagePhone;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PresentMyAccountPageFullName;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PresentMyAccountPageEmailAddress;

    /**
     * Stores the current shipping address being used during checkout.
     */
    public static String PresentMyAccountPagePhone;

    public static int previousnoofcards;
    public static int presentnoofcards;

    public static int previousnoofproductsinwishlist;
    public static int presentnoofproductsinwishlist;

    /**
     * Constant used to store runtime Records
     */
    public static String recordId, giftVoucherAmount, shippingCharges;

    /**
     * Constant used to Store Values in Map of Maps for Comparison
     */
    public static Map<String, Map<String, String>> comparisonMap = new HashMap<String, Map<String, String>>();

    /**
     * Constant used to Store API Response
     */
    public static List<Map<String, Object>> recordsFromAPI;

    /**
     * Constant used to store format used for pricing
     */
    public static final DecimalFormat decfor = new DecimalFormat("0.00");

    /**
     * Constant having expected field values in Map
     */
    public static HashMap<String, String> expectedValuesMap = new HashMap<>();

    /**
     * Constant having expected and actual value to add in report
     */
    public static String actualResult, expectedResult, result;

    /**
     * Constant having expected and actual value to add in report
     */
    public static List<String> resultList = new ArrayList<>();

    /**
     * Constant having API Values in JSON Object
     */
    public static JSONObject recordsJSON;

    /**
     * Constant having API Values in JSON Array
     */
    public static JSONArray recordsJSONArray;

    /**
     * Constant used to store Execution Environment
     */
    public static String env;

    /**
     * Constant used to Product Store Prices
     */
    public static Map<String, Map<String, Object>> productSpecs = new HashMap<String, Map<String, Object>>();

    /**
     * Constant used to Email
     */
    public static String email;

    /**
     * Flag Constant used to runtime flows
     */
    public static int PorductsBeforeFilter;

    /**
     * Flag Constant used to runtime flows
     */
    public static int ProductsAfterFilter;

    /**
     * Flag Constant used to runtime flows
     */
    public static boolean giftVoucherApplied;

    /**
     * Constant having current Scenario Name
     */
    public static Scenario currentScenario;

    /**
     * Constant having Products after Reading from Products.json
     */
    public static org.json.simple.JSONObject productsJsonObj;

    /**
     * Constant used to store runtime Records
     */
    public static String endUserName, accountName, currencyISOCode, cartName, accountCountry, accountStreet,
            accountCity, accountPostalCode, accountState, accountFullCountryName, accountFullStateName, flowName,
            discount, quoteNumber;

    /**
     * Constant used to Product Store Prices
     */
    public static Map<String, Map<String, Object>> cpqQuoteFields = new HashMap<String, Map<String, Object>>();

    /**
     * Constant used to Product Store Prices
     */
    public static Map<String, Map<String, Object>> contractFields = new HashMap<String, Map<String, Object>>();

    /**
     * Constants used to store prices of Products based on tiers
     */
    public static Map<String, Map<String, String>> tierPricing = new HashMap<String, Map<String, String>>();

    /**
     * Constants used to store prices of Products based on tiers
     */
    public static Map<String, Map<String, String>> oneDayPricing = new HashMap<String, Map<String, String>>();

    /**
     * Constant used to store CPQ Quote Field Values
     */
    public static String cpqRegularAmount = "0", cpqNetAmount = "0", cpqPartnerDiscount = "0",
            cpqPartnerDiscAmount = "0", cpqTotalDiscount = "0", cpqTotalDiscountAmount = "0", cpqMRR = "0",
            cpqARR = "0", cpqACV = "0", cpqNetNewACV = "0", cpqTCVOneTime = "0", cpqTCVRecurring = "0",
            cpqTCV = "0";

    /**
     * Constant used to store Contract Fields
     */
    public static String contractAccountId, contractBillTo, contractSoldTo, contract, contractEndUser,
            contractDealId, contractRenewalOpp, contractCurrency, contractPriceBookId,
            contractStartDate, contractEndDate, contractShipTo, contractTerm;

    /**
     * Constant used to store the index for breadcrumbs
     */
    public static int index;

    /**
     * constant used to store the breadcrumb count
     */
    public static int count;

    /**
     * constant used to store the clicked category
     */
    public static String clickedcategory;

    /**
     * constant used to store the product count before removing products from
     * the wishlist
     */
    public static int productcountbeforeremovingfromwishlist;

    /**
     * constant used to store the product count after removing products from the
     * wishlist
     */
    public static int productcountafterremovingfromwishlist;

    /**
     * Stores the Order Number displayed on the Order details page.
     */
    public static String OrderDetailsOrderNumber;

    /**
     * Stores the Order Date displayed on the Order Details page.
     */
    public static String OrderDetailsOrderDate;

    /**
     * Stores the Shipping Address displayed on the Order Details page.
     */
    public static String OrderDetailsShippingAddress;

    /**
     * Stores the Billing Address displayed on the Order Details page.
     */
    public static String OrderDetailsBillingAddress;

    /**
     * Stores the Payment Details displayed on the Order Details page.
     */
    public static String OrderDetailsPaymentDetails;
    /**
     * constant used to store the address count before removing products from
     * the address book
     */
    public static int addresscountbeforeremovingaddress;

    /**
     * constant used to store the address count after removing address from the
     * address book
     */
    public static int addresscountafterremovingaddress;

}
