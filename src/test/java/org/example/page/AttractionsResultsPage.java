package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AttractionsResultsPage {
    WebDriver webDriver;
    WebDriverUtils webDriverUtils;

    static By CITY = className("css-1rrebqu");
    static By ATTRACTION_INFO_BUTTON = className("css-4bo6p0");

    public AttractionsResultsPage(WebDriver webDriver, WebDriverUtils webDriverUtils) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverUtils = webDriverUtils;
    }

    public List<String> getAttractionsCity() {
        List<WebElement> cityName = webDriver.findElements(CITY);

        List<String> cityNameStrings = new ArrayList<>();

        for (WebElement webElement : cityName) {
            cityNameStrings.add(webElement.getText());
            webDriverUtils.waitAndClick(ATTRACTION_INFO_BUTTON);

            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(1));
            webDriver.close();
            webDriver.switchTo().window(tabs.get(0));
        }

        return cityNameStrings;

    }
}
