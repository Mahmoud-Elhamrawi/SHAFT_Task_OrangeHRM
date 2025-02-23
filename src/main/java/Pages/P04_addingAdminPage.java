package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P04_addingAdminPage {

    //Declaration
    SHAFT.GUI.WebDriver driver ;

    //Constructor
    public P04_addingAdminPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver ;
    }

    //Locators
    private final By userRole_checkBox = By.xpath("(//div[@class=\"oxd-select-text--after\"])[1]");
    private final By adminRole_option = By.xpath("//div[@class=\"oxd-select-option\"]/span[.='Admin']") ;


    //  (//div[contains(@class,'oxd-autocomplete-wrapper')]//following::input)[1]
    private final By empName_Inp = By.xpath("(//div[contains(@class,'oxd-autocomplete-wrapper')]//following::input)[1]");
    private final By empName_option = By.cssSelector("div[role=\"option\"] span");

   private final By status_checkBox =  By.xpath("(//div[@class=\"oxd-select-text--after\"])[2]") ;
   private final By status_option = By.xpath("//div[@class=\"oxd-select-option\"]/span[.='Enabled']") ;

   private final By userName_Inp = By.xpath("(//div[contains(@class,'oxd-autocomplete-wrapper')]//following::input)[2]");

   private final By password_Inp = By.xpath("(//input[@type=\"password\"])[1]") ;
   private final By confPass_Inp = By.xpath("(//input[@type=\"password\"])[2]") ;

  private final By submit_Btn = By.cssSelector("button[type=\"submit\"]") ;

  private final By userNameExist = By.cssSelector("p[class=\"oxd-userdropdown-name\"]") ;


  private final By successMSg_Toast = By.xpath("//p[contains(@class,'oxd-text--toast-message')]") ;



    //Method Actions
    public P03_AdminPage addNewAdminUser(String userName ,String empName, String Password)
    {
        driver.element()
                .click(userRole_checkBox).click(adminRole_option)
                .click(empName_Inp).type(empName_Inp,empName).click(empName_option)
                .click(status_checkBox).click(status_option)
                .click(userName_Inp).type(userName_Inp,userName)
                .click(password_Inp).type(password_Inp,Password)
                .click(confPass_Inp).type(confPass_Inp,Password)
                .click(submit_Btn);


        return new P03_AdminPage(driver);
    }

    public String getEmpName()
    {
      return   driver.element().getText(userNameExist);
    }


    public By getMsgContent()
    {
        return successMSg_Toast ;
    }




}
