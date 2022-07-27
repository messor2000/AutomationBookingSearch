package org.example.page;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.inject.Inject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingPage {
    final WebDriver webDriver;
    final WebDriverWait webDriverWait;
    final WebDriverUtils webDriverUtils;

    static By SEARCH_BOX = cssSelector("#ss");
    static By CALENDAR_BODY = By.cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div");
    static By CHECKIN_CALENDAR_BODY = By.xpath("//div[contains(@class, 'b-datepicker')][@data-mode='checkin']");
    //    static By CHECKOUT_CALENDAR_BODY = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp__dates-inner > div:nth-child(3) > div > div > div > div > span");
    static By MONTH = By.cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__content > div:nth-child(1)");
    static By LOGIN_BTN = xpath("//*[@id=\"b2indexPage\"]/header/nav[1]/div[2]/div[6]/a");
    static By CHECKIN_DATE = cssSelector(
            "#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__content > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(1)");
    static By CHECKOUT_DATE = cssSelector(
            "#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__content > div:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(3)");

    static By SEARCH_BUTTON = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__button > div.sb-searchbox-submit-col.-submit-button > button");

    public BookingPage(WebDriver webDriver, WebDriverWait webDriverWait, WebDriverUtils webDriverUtils) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriverUtils = webDriverUtils;
    }

    public void expectElements() {
        webDriverWait.until(visibilityOfElementLocated(SEARCH_BOX));
    }

    public void clickLoginBtn() {
        webDriverUtils.waitAndClick(LOGIN_BTN);
    }

    public void enterSearch(String searchTerm) {
        webDriverUtils.type(SEARCH_BOX, searchTerm);
    }

    @SneakyThrows
    public void setDate() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(CHECKIN_CALENDAR_BODY)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(CALENDAR_BODY));

        selectDate("December 2022", "1");
        selectDate("August 2022", "31");

    }

    public void selectDate(String month_year, String select_day) throws InterruptedException {
        WebElement button = webDriver.findElement(cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__control.bui-calendar__control--next > svg"));
        WebElement monthYear = webDriver.findElement(xpath("//*[contains(@class, 'bui-calendar__month')]"));

        if (!monthYear.getText().equals(month_year)) {
            button.click();
        } else {
            List<WebElement> days = webDriver.findElements(cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__content > div:nth-child(2) > table > tbody"));
            for (WebElement day : days) {
                if (day.getText().equals(select_day)) {
                    day.click();
                    Thread.sleep(10000);
                    return;
                }
            }
        }
    }

    public void clickSearchBtn() {
        webDriverUtils.waitAndClick(SEARCH_BUTTON);
    }
}
