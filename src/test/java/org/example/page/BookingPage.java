package org.example.page;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    static By CHECKIN_CALENDAR_BODY = xpath("//div[contains(@class, 'b-datepicker')][@data-mode='checkin']");
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
//        selectDate();
        enterDate();
    }

//    private void selectDate() {
//        WebElement button = webDriver.findElement(xpath("//*[contains(@data-bui-ref, 'calendar-next')]"));
//        List<WebElement> monthYear;
//
//        for (int i = 0; i < 11; i++) {
//            monthYear = webDriver.findElements(xpath("//*[contains(@class, 'bui-calendar__month')]"));
//            if (!monthYear.get(0).getText().equals("December 2022")) {
//                button.click();
//            } else {
//                webDriverUtils.waitAndClick(xpath("//*[contains(@data-date, '2022-12-01')]"));
//                webDriverUtils.waitAndClick(xpath("//*[contains(@data-date, '2022-12-31')]"));
//            }
//        }
//    }

    private void enterDate() {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("document.getElementsByName('checkin_year')[0].setAttribute('type', 'text');");
        webDriver.findElement(By.xpath("//input[@name='checkin_year']")).clear();
        webDriver.findElement(By.xpath("//input[@name='checkin_year']")).sendKeys("2022");

        jse.executeScript("document.getElementsByName('checkin_month')[0].setAttribute('type', 'text');");
        webDriver.findElement(By.xpath("//input[@name='checkin_month']")).clear();
        webDriver.findElement(By.xpath("//input[@name='checkin_month']")).sendKeys("12");

        Select checkInDropdown = new Select(webDriver.findElement(xpath("//select[@name='checkin_monthday']")));
        checkInDropdown.selectByValue("1");

        jse.executeScript("document.getElementsByName('checkout_year')[0].setAttribute('type', 'text');");
        webDriver.findElement(By.xpath("//input[@name='checkout_year']")).clear();
        webDriver.findElement(By.xpath("//input[@name='checkout_year']")).sendKeys("2022");

        jse.executeScript("document.getElementsByName('checkout_month')[0].setAttribute('type', 'text');");
        webDriver.findElement(By.xpath("//input[@name='checkout_month']")).clear();
        webDriver.findElement(By.xpath("//input[@name='checkout_month']")).sendKeys("12");

        Select checkOutDropdown = new Select(webDriver.findElement(xpath("//select[@name='checkout_monthday']")));
        checkOutDropdown.selectByValue("31");
    }

    public void clickSearchBtn() {
        webDriverUtils.waitAndClick(SEARCH_BUTTON);
    }

    public void clickAttractionButton() {
        webDriverUtils.waitAndClick(ATTRACTIONS_BUTTON);
    }
}
