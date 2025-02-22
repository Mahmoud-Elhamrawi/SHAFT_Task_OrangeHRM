package TestCases;

import Pages.P00_LoginPage;
import Pages.P01_DashboardPage;
import Pages.P03_AdminPage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC00 {

    //declaration
    SHAFT.GUI.WebDriver driver ;
    SHAFT.TestData.JSON tData ;
    public static String numbersOfRecords ="";

    @BeforeClass
    public void SetUp()
    {
        driver = new SHAFT.GUI.WebDriver();
        tData = new SHAFT.TestData.JSON(SHAFT.Properties.paths.testData()+"loginData.json");
        driver.browser().navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @Test
    public void taskEnd2End()
    {

        //Login and navigate to dashboard
      new P00_LoginPage(driver)
              .loginTC(tData.getTestData("userName"),tData.getTestData("password"));

      driver.browser().assertThat().url().isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index").perform();
      driver.element().verifyThat(new P01_DashboardPage(driver).getLocatorText()).text().isEqualTo("Dashboard").perform();


      // navigate from dashboard  to Admin Page
        new P01_DashboardPage(driver).navigateToAdminPage() ;
        driver.browser().assertThat().url().contains("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers").perform();
        driver.element().verifyThat(new P03_AdminPage(driver).getByLocatorText()).text().isEqualTo("Admin").perform();

       // get numbers of records

        numbersOfRecords =new P03_AdminPage(driver).getNumbersOfRecords() ;
        System.out.println("numbersOfRecords : " + numbersOfRecords) ;


    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }



}
