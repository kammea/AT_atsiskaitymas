package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CommonPage;
import pages.ProductPage;

import java.awt.*;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CategoriesTest extends BaseTest {

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {"iPod Nano"},
                {"iPod Touch"},
                {"iPod Shuffle"}
        };
    }

    @Test(dataProvider = "data")
    public void categories(String productName) throws InterruptedException {
        String categoryName = "MP3 Players";
        CommonPage commonPage = new CommonPage(driver);
        ProductPage productPage = new ProductPage(driver);

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.partialLinkText(categoryName))).build().perform();

        commonPage.clickShowAllMP3Button();

        String actualCategoryName = productPage.getProductTitleText();
        System.out.println(actualCategoryName);
        assertEquals(actualCategoryName, categoryName, "Kategorijos pavadinimas ne toks kokio tikėtasi: \n" + actualCategoryName);

        productPage.clickListViewButton();

        List<WebElement> products = productPage.getAllProducts();
        for (WebElement product : products) {
            if (product.findElement(productPage.getProductName()).getText().equals(productName)) {
                product.findElement(productPage.getCartButton()).click();
                break;
            }
        }
        Thread.sleep(3000);
        String realAlertMessage = productPage.getAlertText();
        String expectedAlertMessage = "Success: You have added " + productName + " to your shopping cart!";
        assertTrue(realAlertMessage.contains(expectedAlertMessage), "Alert žinutė ne tokia, kokios tikėtasi: \n" + realAlertMessage);

        productPage.clickOnCart();


        String productInCart = productPage.productInCart();
        System.out.println(productInCart);
        assertEquals(productInCart, productName, "Produktas ne toks, kokio tikėtasi \n" + productInCart);
    }
}
