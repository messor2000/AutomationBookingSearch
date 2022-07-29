package org.example.page;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingPage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    WebDriverUtils webDriverUtils;

    static By SEARCH_BOX = cssSelector("#ss");
    static By CALENDAR_BODY = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div");
    static By CHECKIN_CALENDAR_BODY = xpath("//div[contains(@class, 'b-datepicker')][@data-mode='checkin']");
    static By CHECK_IN_MONTH_YEAR = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp__dates-inner > div:nth-child(2) > div > div > div > div > div.sb-date-field__controls.sb-date-field__controls__ie-fix > div.sb-date-field__select.-month-year.js-date-field__part > select > option:nth-child(7)");
    //    static By CHECK_IN_DAY = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp__dates-inner > div:nth-child(2) > div > div > div > div > div.sb-date-field__controls.sb-date-field__controls__ie-fix > div.sb-date-field__select.-day.js-date-field__part.sb-date-field__select_disabled > select");
    static By CHECK_OUT_MONTH_YEAR = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp__dates-inner > div:nth-child(3) > div > div > div > div > div.sb-date-field__controls.sb-date-field__controls__ie-fix > div.sb-date-field__select.-month-year.js-date-field__part > select > option:nth-child(7)");
//    static By CHECK_OUT_DAY = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp__dates-inner > div:nth-child(3) > div > div > div > div > div.sb-date-field__controls.sb-date-field__controls__ie-fix > div.sb-date-field__select.-day.js-date-field__part.sb-date-field__select_disabled > select");

    static By CHECKIN_MONTH = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__content > div:nth-child(1)");

    static By CHECK_IN_DAY = xpath("//*[contains(@data-date, '2022-12-01')]");
    static By CHECK_OUT_DAY = xpath("//*[contains(@data-date, '2022-12-31')]");
    static By CALENDAR_ROW = xpath("//div[contains(@class, 'bui-calendar__row')]");

    static By ATTRACTIONS_BUTTON = xpath("//*[@id=\"b2indexPage\"]/header/nav[2]/ul/li[4]/a");
    static By SEARCH_BUTTON = cssSelector("#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__button > div.sb-searchbox-submit-col.-submit-button > button");

    public BookingPage(WebDriver webDriver, WebDriverWait webDriverWait, WebDriverUtils webDriverUtils) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriverUtils = webDriverUtils;
    }

    public void expectElements() {
        webDriverWait.until(visibilityOfElementLocated(SEARCH_BOX));
    }

    public void enterSearch(String searchTerm) {
        webDriverUtils.type(SEARCH_BOX, searchTerm);
    }

    @SneakyThrows
    public void setDate() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(CHECKIN_CALENDAR_BODY)).click();
        selectDate();
    }

    private void selectDate() {
        WebElement button = webDriver.findElement(xpath("//*[contains(@data-bui-ref, 'calendar-next')]"));
        List<WebElement> monthYear;

        for (int i = 0; i < 11; i++) {
            monthYear = webDriver.findElements(xpath("//*[contains(@class, 'bui-calendar__month')]"));
            if (!monthYear.get(0).getText().equals("December 2022")) {
                button.click();
            } else {
                webDriverUtils.waitAndClick(xpath("//*[contains(@data-date, '2022-12-01')]"));
                webDriverUtils.waitAndClick(xpath("//*[contains(@data-date, '2022-12-31')]"));
            }
        }
    }

    public void clickSearchBtn() {
        webDriverUtils.waitAndClick(SEARCH_BUTTON);
    }

    public void clickAttractionButton() {
        webDriverUtils.waitAndClick(ATTRACTIONS_BUTTON);
    }
}
