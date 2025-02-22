package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P01_DashboardPage {
    //declaration
    private final SHAFT.GUI.WebDriver driver;


    //Constructor
    public P01_DashboardPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver ;
    }

    List<WebElement> list ;


    //Locators

    private final By assertOnText = By.xpath("//h6[.='Dashboard']");
    private final By lis_dash = By.cssSelector("li[class=\"oxd-main-menu-item-wrapper\"] span");





    //Methods Actions
    public  By getLocatorText()
    {
       return assertOnText ;
    }

    public P03_AdminPage navigateToAdminPage()
    {
        list =driver.getDriver().findElements(lis_dash); // 12 options
        for (int i =0 ;i<list.size() ;i++)
        {
           WebElement index =list.get(i);
           String textNedd = index.getText();
             if(textNedd.equalsIgnoreCase("Admin"))
             {
                driver.getDriver().findElements(lis_dash).get(i).click();
                 break;
             }



        }


        return new P03_AdminPage(driver);
    }







}
