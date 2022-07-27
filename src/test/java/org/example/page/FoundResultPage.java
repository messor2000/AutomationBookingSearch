package org.example.page;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoundResultPage {
    final WebDriver webDriver;
    final WebDriverWait webDriverWait;
    final WebDriverUtils webDriverUtils;

    static By SEARCH_BUTTON = cssSelector("#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(6) > div > button");
    static By CHECKIN_DATE_BUTTON = cssSelector(
            "#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(2) > button");

    static By CHECKOUT_DATE_BUTTON = cssSelector(
            "#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(4) > button");

    static By CALENDAR_DATEPICKER = cssSelector("#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(2) > div");

    static By MONTH_YEAR = cssSelector("#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(2) > div > div > div.fa3f76ae6b > div:nth-child(1) > h3");
    static By NEXT_BUTTON = cssSelector("#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(2) > div > div > button.fc63351294.a822bdf511.e3c025e003.fa565176a8.cfb238afa1.ae1678b153.c9fa5fc96d.be298b15fa");
    static By CHECKIN_DATE = cssSelector("#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(2) > div > div > div.fa3f76ae6b > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(4)");
    static By CHECKOUT_DATE = cssSelector("#left_col_wrapper > div:nth-child(1) > div > div > form > div > div:nth-child(3) > div:nth-child(2) > div > div > div.fa3f76ae6b > div:nth-child(2) > table > tbody > tr:nth-child(5) > td:nth-child(6)");


    public FoundResultPage(WebDriver webDriver, WebDriverWait webDriverWait, WebDriverUtils webDriverUtils) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriverUtils = webDriverUtils;
    }

    public void expectElements() {
        webDriverWait.until(visibilityOfElementLocated(SEARCH_BUTTON));
        webDriverWait.until(visibilityOfElementLocated(CHECKIN_DATE_BUTTON));
        webDriverWait.until(visibilityOfElementLocated(CHECKOUT_DATE_BUTTON));
    }

    @SneakyThrows
    public void setDates() {
        webDriverUtils.waitAndClick(CHECKIN_DATE_BUTTON);
        setDate("December", "2022");

        webDriverUtils.waitAndClick(CHECKOUT_DATE_BUTTON);
        setDate("December", "2022");
    }

    public void clickSearchBtn() {
        webDriverUtils.waitAndClick(SEARCH_BUTTON);
    }

    public List<String> clickHotelButtonAndGetHotelInfo() {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        List<WebElement> allLinks = getAllHotelLinks();
        List<String> hotelAddress = new ArrayList<>();
        for (WebElement element: allLinks) {
            webDriverWait.until(elementToBeClickable(element));
            element.click();
            Hotel hotel = new Hotel(webDriver, webDriverWait, webDriverUtils);
            hotelAddress.add(hotel.getFullAddress());
            webDriver.switchTo().window(tabs.get(0));
        }

        return hotelAddress;
    }

    private List<WebElement> getAllHotelLinks() {
        return webDriver.findElements(By.className("a4225678b2"));
    }

//    public void selectDate(String month_year, String select_day) throws InterruptedException {
//        List<WebElement> elements = webDriver.findElements(By.xpath("//*[@id=\"left_col_wrapper\"]/div[1]/div/div/form/div/div[3]/div[2]/div/div/div[1]"));
//
//        for (WebElement element : elements) {
//            System.out.println(element.getText());
//
////Selecting the month
//            if (element.getText().equals(month_year)) {
//
////Selecting the date
//                List<WebElement> days = webDriver.findElements(By.xpath("//*[@id=\"left_col_wrapper\"]/div[1]/div/div/form/div/div[3]/div[2]/div/div/div[1]/div[2]/table"));
//
//                for (WebElement d : days) {
//                    System.out.println(d.getText());
//                    if (d.getText().equals(select_day)) {
//                        d.click();
//                        Thread.sleep(10000);
//                        return;
//                    }
//                }
//            }
//        }
//    }


    private void setDate(String month, String year) {
//        webDriverWait.until(visibilityOfElementLocated(CALENDAR_DATEPICKER));

        WebElement calendar = webDriver.findElement(CALENDAR_DATEPICKER);
        String month2 = calendar.getText();
        System.out.println(month2);

        String monthYear = webDriver.findElement(MONTH_YEAR).getText();

        while (!getMonthYear(monthYear)[0].equals(month) && getMonthYear(monthYear)[1].equals(year)) {
            webDriver.findElement(NEXT_BUTTON).click();
            monthYear = webDriver.findElement(MONTH_YEAR).getText();
        }
        webDriver.findElement(CHECKIN_DATE).click();
    }

    private String[] getMonthYear(String monthYear) {
        return monthYear.split(" ");
    }
}
