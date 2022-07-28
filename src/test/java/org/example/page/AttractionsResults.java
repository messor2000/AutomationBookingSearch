package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AttractionsResults {
    WebDriver webDriver;

    static By CITY = className("css-1rrebqu");

    public AttractionsResults(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public List<String> getAttractionsCity() {
        List<WebElement> cityName = webDriver.findElements(CITY);

        List<String> cityNameStrings = new ArrayList<>();

        for (WebElement webElement : cityName) {
            cityNameStrings.add(webElement.getText());
        }

        return cityNameStrings;

    }
}
