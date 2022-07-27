package org.example.page;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Hotel {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    WebDriverUtils webDriverUtils;

    Hotel(WebDriver webDriver, WebDriverWait webDriverWait, WebDriverUtils webDriverUtils) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriverUtils = webDriverUtils;
    }

    static By ADDRESS = cssSelector("#breadcrumb > ol > li:nth-child(9) > div > div > h1 > a");

    public String getFullAddress() {
        webDriverWait.until(visibilityOfElementLocated(ADDRESS));
        WebElement address = webDriver.findElement(ADDRESS);

        return address.getText();
    }
}

