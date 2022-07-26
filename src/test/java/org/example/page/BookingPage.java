package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.inject.Inject;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingPage {
    final WebDriver webDriver;
    final WebDriverWait webDriverWait;
    final WebDriverUtils webDriverUtils;

    static By SEARCH_BOX = xpath("//*[@id=\"ss\"]");
    static By CHECKIN_CALENDAR_BODY = xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[2]/div/div/div/div/span");
    static By CHECKOUT_CALENDAR_BODY = xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[3]/div/div/div/div/span");

    static By LOGIN_BTN = xpath("//*[@id=\"b2indexPage\"]/header/nav[1]/div[2]/div[6]/a");

    static By SUGGESTIONS_LIST = xpath("//*[@id=\"frm\"]/div[1]/div[1]/div[1]/div[1]/ul[1]");
    static By CHECK_OUT_DATEPICKER = cssSelector(
            ".sb-searchbox-universal .--checkout-field .b-datepicker .sb-searchbox__input:not(.-empty)");
    static By CHECKIN_CURRENT_MONTH_LAST_WEEK = cssSelector(
            ".c2-month:first-of-type tbody tr:last-of-type .c2-day");
    static By CHECKOUT_NEXT_MONTH_FIRST_WEEK = cssSelector(
            ".c2-month:nth-of-type(2) tbody tr:first-of-type .c2-day");


    static By SEARCH_BUTTON = cssSelector(".sb-searchbox-universal .sb-searchbox-submit-col button");

    @Inject
    public BookingPage(WebDriver webDriver, WebDriverWait webDriverWait, WebDriverUtils webDriverUtils) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriverUtils = webDriverUtils;
    }


//    @FindBy(xpath = "//*[contains(@id, 'ss')]")
//    WebElement searchInput;
//
//    @FindBy(xpath = "//*[contains(@id, 'frm')]")
//    WebElement checkInDate;
//
//    @FindBy(xpath = "//*[contains(@id, 'frm')]")
//    WebElement checkOutDate;
//
//    @FindBy(xpath = "//*[contains(text(), 'Search')]/..")
//    WebElement searchBtn;
//    @FindBy(xpath = "//*[@id=\"b2indexPage\"]/header/nav[1]/div[2]/div[6]/a")
//    WebElement loginBtn;

    public void expectElements() {
        webDriverWait.until(visibilityOfElementLocated(SEARCH_BOX));
        webDriverWait.until(visibilityOfElementLocated(CHECKIN_CALENDAR_BODY));
        webDriverWait.until(visibilityOfElementLocated(CHECKOUT_CALENDAR_BODY));
    }

    public void clickLoginBtn() {
        webDriverUtils.waitAndClick(LOGIN_BTN);
    }

    public void enterSearch(String searchTerm) {
        webDriverUtils.type(SEARCH_BOX, searchTerm);
        webDriverWait.until(elementToBeClickable(SUGGESTIONS_LIST));
        webDriver.findElements(SUGGESTIONS_LIST).stream()
                .filter(el -> el.getAttribute("data-label").equals(searchTerm))
                .findFirst().orElseThrow(NotFoundException::new)
                .click();
        webDriverWait.until(elementToBeClickable(SUGGESTIONS_LIST));
    }

    public void setCheckInDate() {
        webDriverWait.until(visibilityOfElementLocated(CHECKIN_CALENDAR_BODY));
        List<WebElement> daysOfLastWeek = webDriver.findElement(CHECKIN_CALENDAR_BODY)
                .findElements(CHECKIN_CURRENT_MONTH_LAST_WEEK);
        daysOfLastWeek.get(daysOfLastWeek.size() - 1).click();
        webDriverWait.until(invisibilityOfElementLocated(CHECKIN_CALENDAR_BODY));
    }

    public void setCheckOutDate() {
        webDriverWait.until(elementToBeClickable(CHECK_OUT_DATEPICKER));
        webDriver.findElement(CHECK_OUT_DATEPICKER).click();
        webDriverWait.until(visibilityOfElementLocated(CHECKOUT_CALENDAR_BODY));
        webDriver.findElement(CHECKOUT_CALENDAR_BODY)
                .findElements(CHECKOUT_NEXT_MONTH_FIRST_WEEK)
                .get(0).click();
        webDriverWait.until(invisibilityOfElementLocated(CHECKOUT_CALENDAR_BODY));
    }

//    public void inputCity(String city) {
//        searchInput.sendKeys(city);
//    }
//
//    public void inputCheckInDate(String date) {
//        checkInDate.sendKeys(date);
//    }
//
//    public void inputCheckOutDate(String date) {
//        checkOutDate.sendKeys(date);
//    }

    public void clickSearchBtn() {
//        searchBtn.click();
        webDriverUtils.waitAndClick(SEARCH_BUTTON);
    }
}
