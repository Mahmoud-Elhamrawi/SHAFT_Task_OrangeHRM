package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P00_LoginPage {
    //Declaration
    SHAFT.GUI.WebDriver driver ;

    //constructor
    public P00_LoginPage(SHAFT.GUI.WebDriver driver)
    {
    this.driver = driver ;
    }

    //Locators
    private final By userName_imp = By.cssSelector("input[name=\"username\"]");
    private final By userPassword_inp = By.cssSelector("input[name=\"password\"]");
    private final By login_Btn = By.cssSelector("button[type=\"submit\"]");





    //Method Actions
   public P01_DashboardPage loginTC(String uName , String Upass)
   {
       driver.element().type(userName_imp,uName).type(userPassword_inp , Upass).click(login_Btn);
       return new P01_DashboardPage(driver);
   }



}