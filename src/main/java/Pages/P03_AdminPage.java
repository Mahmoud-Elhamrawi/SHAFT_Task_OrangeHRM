package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P03_AdminPage
{
    //declaration

    SHAFT.GUI.WebDriver driver ;

    //Constructor
    public  P03_AdminPage(SHAFT.GUI.WebDriver driver)
    {
        this.driver = driver ;
    }

    //Locators
   private final By assertOnText = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");
   private final By numbersOfRecords = By.xpath("//div[contains(@class,'orangehrm-vertical-padding')]/span");



    //Methods Actions
    public By getByLocatorText()
    {
        return assertOnText ;
    }

// (15) Records Found
    public String getNumbersOfRecords()
    {
        return driver.element().getText(numbersOfRecords).replaceAll("[^0-9]","");
    }












}
