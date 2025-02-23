package TestCases;

import Pages.P00_LoginPage;
import Pages.P01_DashboardPage;
import Pages.P03_AdminPage;
import Pages.P04_addingAdminPage;
import com.shaft.driver.SHAFT;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;

public class TC00 {

    //declaration
    SHAFT.GUI.WebDriver driver ;
    SHAFT.TestData.JSON tData,tDataEmp ;

    public static float numbersOfRecordsBefore =0;
    public static float numbersOfRecordsAfter =0;
    public static float numbersOfRecordsAfterDeleted=0;

    @BeforeClass
    public void SetUp()
    {
        driver = new SHAFT.GUI.WebDriver();
        tData = new SHAFT.TestData.JSON(SHAFT.Properties.paths.testData()+"loginData.json");
        tDataEmp = new SHAFT.TestData.JSON(SHAFT.Properties.paths.testData()+"dataTask.json");

        driver.browser().navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @Test
    public void taskEnd2End() throws InterruptedException {

        //Login and navigate to dashboard
      new P00_LoginPage(driver)
              .loginTC(tData.getTestData("userName"),tData.getTestData("password"));

      driver.browser().assertThat().url().isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index").perform();
      driver.element().verifyThat(new P01_DashboardPage(driver).getLocatorText()).text().isEqualTo("Dashboard").perform();


      // navigate from dashboard  to Admin Page
        new P01_DashboardPage(driver).navigateToAdminPage() ;
        driver.browser().assertThat().url().contains("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers").perform();
        driver.element().verifyThat(new P03_AdminPage(driver).getByLocatorText()).text().isEqualTo("Admin").perform();

       // get  current numbers of records
        numbersOfRecordsBefore = Float.parseFloat(new P03_AdminPage(driver).getNumbersOfRecords());


       //navigate to adding Admin user page
        new P03_AdminPage(driver).addNewUser();
        driver.browser().assertThat().url().contains("admin/saveSystemUser");

        String empName = new P04_addingAdminPage(driver).getEmpName() ;


        //add new Admin User
         new P04_addingAdminPage(driver)
                 .addNewAdminUser(tDataEmp.getTestData("userNameEmp"),empName,tDataEmp.getTestData("password")) ;

         //assert on meg success
        driver.element().assertThat(new P04_addingAdminPage(driver).getMsgContent()).text().isEqualTo("Successfully Saved").perform();


        // get numbers of records after added
        numbersOfRecordsAfter = Float.parseFloat(new P03_AdminPage(driver).getNumbersOfRecords());


        //verify numbers of records increase by 1
        SHAFT.Validations.assertThat().number(numbersOfRecordsAfter).isEqualTo(numbersOfRecordsBefore+1);

       //search for adding User
        new P03_AdminPage(driver)
                .searchForUser(tDataEmp.getTestData("userNameEmp"));

        //verify on user that exist
          driver.element().assertThat(new P03_AdminPage(driver).assertTxt()).text().isEqualTo(tDataEmp.getTestData("userNameEmp")).perform();

          //Delete New User
        new P03_AdminPage(driver).deleteExistUser() ;
        driver.browser().refreshCurrentPage() ;



        // get numbers of records after delete
        numbersOfRecordsAfterDeleted = Float.parseFloat(new P03_AdminPage(driver).getNumbersOfRecords());


      //  verify numbers of records de-creased by 1
        SHAFT.Validations.assertThat().number(numbersOfRecordsBefore).isEqualTo(numbersOfRecordsAfterDeleted);


    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }



}
