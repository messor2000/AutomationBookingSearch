package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {
    WebDriver webDriver;

    Hotel(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[contains(@id, 'showMap2')]")
    WebElement location;

    //    @FindBy(xpath = "//*[contains(@id, 'hp_availability_style_changes')]")
    @FindBy(xpath = "//*[@id=\"hp_availability_style_changes\"]/div[2]/form/div/div[1]/div[1]/div[2]/div/div/div/div/span")
    WebElement checkInDate;

    //    @FindBy(xpath = "//*[contains(@id, 'hp_availability_style_changes')]")
    @FindBy(xpath = "//*[@id=\"hp_availability_style_changes\"]/div[2]/form/div/div[1]/div[1]/div[3]/div/div/div/div/span")
    WebElement checkOutDate;

    public String getLocation() {
        return location.getText();
    }

    public String getCheckInDate() {
        return checkInDate.getText();
    }

    public String getCheckOutDate() {
        return checkOutDate.getText();
    }
}

//*[@id="showMap2"]/span[1]
//*[@id="hp_availability_style_changes"]/div[2]/form/div/div[1]/div[1]/div[2]/div/div/div/div/span
//*[@id="hp_availability_style_changes"]/div[2]/form/div/div[1]/div[1]/div[3]/div/div/div/div/span
