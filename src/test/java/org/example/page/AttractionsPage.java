package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AttractionsPage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    WebDriverUtils webDriverUtils;

    static By SEARCH_BOX = className("css-1hf24bo");
    static By SEARCH_BUTTON = xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div/div[3]/div/div/div/form/button");

    public AttractionsPage(WebDriver webDriver, WebDriverWait webDriverWait, WebDriverUtils webDriverUtils) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.webDriverUtils = webDriverUtils;
    }

    public void expectElements() {
        webDriverWait.until(visibilityOfElementLocated(SEARCH_BOX));
        webDriverWait.until(visibilityOfElementLocated(SEARCH_BUTTON));
    }

    public void enterSearch(String searchTerm) {
        webDriverUtils.type(SEARCH_BOX, searchTerm);
    }

    public void clickSearchButton() {
        webDriverUtils.waitAndClick(SEARCH_BUTTON);
    }
}
