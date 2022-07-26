package org.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.page.BookingPage;
import org.example.page.FoundResultPage;
import org.example.util.ConfProperties;
import org.example.util.WebDriverUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class TakeResultsFromPageTests {
    private static BookingPage bookingPage;
    private static FoundResultPage foundResultPage;
    private static WebDriver driver;
    private static WebDriverWait webDriverWait;
    private static WebDriverUtils webDriverUtils;

    @SneakyThrows
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverUtils = new WebDriverUtils(driver, webDriverWait);
        bookingPage = new BookingPage(driver, webDriverWait, webDriverUtils);
        foundResultPage = new FoundResultPage(driver);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("page"));
    }

    @Test
    @DisplayName(value = "Should return results which which matches the entered data")
    public void getCorrectDataAfterSearch() {
//        bookingPage.inputCity("New York");
//        bookingPage.inputCheckInDate(ConfProperties.getProperty("checkInDate"));
//        bookingPage.inputCheckOutDate(ConfProperties.getProperty("checkOutDate"));
//
//        bookingPage.clickSearchBtn();

        bookingPage.expectElements();
        bookingPage.enterSearch(ConfProperties.getProperty("city"));
        bookingPage.clickSearchBtn();
    }

    @Test
    public void loginTest() {
        bookingPage.clickLoginBtn();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
