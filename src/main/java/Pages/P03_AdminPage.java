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
   private final By addUSer_Btn = By.xpath("//div[@class=\"orangehrm-header-container\"]/button");

   private final By userNameSearch_Inp = By.xpath("//div[contains(@class,'oxd-input-group')]/div/input") ;
   private final By search_Btn = By.cssSelector("button[type=\"submit\"]") ;
   private final By assertText_Inp=By.xpath("(//div[@class=\"oxd-table-body\"] //div[contains(@class,'oxd-table-cell')])[2]") ;
   private final By delete_Btn = By.xpath("(//button[contains(@class,'oxd-table-cell-action-space')])[1]") ;
   private final By confirmDelete_Btn = By.xpath("//button[contains(@class,'oxd-button--label-danger')]") ;


//   private final By deleteMSg_Toast = By.xpath("//p[contains(@class,'oxd-text--toast-message')]");

    //Methods Actions
    public By getByLocatorText()
    {
        return assertOnText ;
    }

   //   Get # of records -> (15) Records Found
    public String getNumbersOfRecords()
    {
        return driver.element().getText(numbersOfRecords).replaceAll("[^0-9]","");
    }

    //navigate to Adding admin page
    public P04_addingAdminPage addNewUser()
    {
        driver.element().click(addUSer_Btn);
        return new P04_addingAdminPage(driver);
    }

    //search for user that added
    public P03_AdminPage searchForUser(String  UName)
    {
        driver.element().type(userNameSearch_Inp,UName).click(search_Btn) ;

        return this ;
    }
     public By assertTxt()
     {
      return  assertText_Inp ;
     }

     //Delete exist User
    public P03_AdminPage deleteExistUser()
    {
        driver.element().click(delete_Btn).click(confirmDelete_Btn);
        return this;
    }














}
