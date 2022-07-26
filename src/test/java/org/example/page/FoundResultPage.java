package org.example.page;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoundResultPage {
    WebDriver webDriver;

    public FoundResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

//    @FindBy(xpath = "//*[contains(@id, 'search_results_table')]")
//    WebElement location;
//
//    @FindBy(xpath = "//*[@id=\"left_col_wrapper\"]/div[1]/div/div/form/div/div[3]/div[2]/button")
//    WebElement checkInDate;
//
//    @FindBy(xpath = "//*[@id=\"left_col_wrapper\"]/div[1]/div/div/form/div/div[3]/div[4]/button")
//    WebElement checkOutDate;

    @FindBy(xpath = "//*[@id=\"search_results_table\"]/div[2]/div/div/div/div[9]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div/div[1]/div/h3/a/div[1]")
    List<String> hotelList;

    public List<String> getHotelList() {
        return hotelList;
    }

//    public String getLocation() {
//        return location.getText();
//    }
//
//    public String getCheckInDate() {
//        return checkInDate.getText();
//    }
//
//    public String getCheckOutDate() {
//        return checkOutDate.getText();
//    }
}
