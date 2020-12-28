package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pages.CommonPage;
import pages.LoginPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest{

    @Test
    public void login(){
        String email = "test@gmail.com";
        String password = "testas";
        String expectedWarningText = "Warning: No match for E-Mail Address and/or Password.";

        LoginPage loginPage = new LoginPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        commonPage.clickOnMyAccont();
        commonPage.clickOnLogin();

        List<WebElement> customerBlocks = loginPage.getAllCustomerBlocks();
        for (WebElement block : customerBlocks) {
            System.out.println(block.getText());
            assertTrue(block.isDisplayed());
        }

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue(loginPage.getAlert().isDisplayed());

        String alertWarningText = loginPage.getAlert().getText();
        System.out.println(alertWarningText);
        assertEquals(alertWarningText,expectedWarningText,"Warning tekstas kitoks nei tikėtasi: \n" + alertWarningText);

    }

}
