package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.entity.Hotel;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.className;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsResultPage {
    WebDriver webDriver;

    static By HOTEL_NAME = className("fcab3ed991");
    static By ADDRESS =  className("a1fbd102d9");
    static By HOTEL_BUTTON = className("e13098a59f");

    public HotelsResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public List<Hotel> checkHotelRequirements() {
        List<WebElement> cards = getAllHotelCards();
        List<Hotel> hotelList = new ArrayList<>();
        List<WebElement> listOfNames = webDriver.findElements(HOTEL_NAME);
        List<WebElement> listOfAddress = webDriver.findElements(ADDRESS);

        for (int i = 0; i < cards.size(); i++) {
            Hotel hotel = new Hotel();
            hotel.setName(listOfNames.get(i).getText());
            hotel.setAddress(listOfAddress.get(i).getText());
            hotelList.add(hotel);
            cards.get(i).findElement(HOTEL_BUTTON).click();

            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(1));
            webDriver.close();
            webDriver.switchTo().window(tabs.get(0));
        }

        return hotelList;
    }

    private List<WebElement> getAllHotelCards() {
        return webDriver.findElements(className("d20f4628d0"));
    }
}
