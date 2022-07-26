package org.example.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebDriverUtils {

    WebDriver webDriver;
    WebDriverWait wait;

    public WebDriverUtils(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    public void waitAndClick(By selector) {
        wait.until(elementToBeClickable(selector));
        webDriver.findElement(selector).click();
    }

    public void type(By selector, String text) {
        wait.until(visibilityOfElementLocated(selector));
        webDriver.findElement(selector).sendKeys(text);
    }
}
