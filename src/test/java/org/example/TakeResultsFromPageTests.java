package org.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.page.*;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TakeResultsFromPageTests {
    private static BookingPage bookingPage;
    private static HotelsResultPage hotelsResultPage;
    private static AttractionsPage attractionsPage;
    private static AttractionsResults attractionsResult;
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
        hotelsResultPage = new HotelsResultPage(driver, webDriverWait, webDriverUtils);
        attractionsPage = new AttractionsPage(driver, webDriverWait, webDriverUtils);
        attractionsResult = new AttractionsResults(driver);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("page"));
    }

    @Test
    @DisplayName("should return results which matches the entered data and city")
    public void getCorrectDataAfterSearch() {
        bookingPage.expectElements();
        bookingPage.enterSearch(ConfProperties.getProperty("city"));
        bookingPage.setDate();
        bookingPage.clickSearchBtn();

        List<Hotel> hotelList = hotelsResultPage.checkHotelRequirements();
        for (Hotel hotel: hotelList) {
            assertTrue(hotel.getAddress().contains(ConfProperties.getProperty("city")));
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("should return correct attractions which have selected requirements")
    public void getCorrectDataAfterSearchInAttraction() {
        bookingPage.clickAttractionButton();

        attractionsPage.expectElements();
        attractionsPage.enterSearch(ConfProperties.getProperty("city"));
        attractionsPage.clickSearchButton();

        for (String city: attractionsResult.getAttractionsCity()) {
            assertTrue(city.contains(ConfProperties.getProperty("city")));
        }
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
