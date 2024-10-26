package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddToCart_Remove_Steps {

    WebDriver driver = Hooks.getDriver();

    @Given("I am logged into Swag Labs website successfully")
    public void iAmLoggedIntoSwagLabsWebsiteSuccessfully() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=\"Login\"]")).click();
    }

    @When("I add product to the cart")
    public void iAddProductToTheCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("the 'Add to cart' button should change to 'Remove' button")
    public void theAddToCartButtonShouldChangeToRemoveButton() {
        //wait briefly to ensure the page updates
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
        //check if the button changed to "Remove"
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        //verify that the remove button is displayed
        Assert.assertTrue(removeButton.isDisplayed());
    }

    @Then("number of product added will be shown on the cart")
    public void numberOfProductAddedWillBeShownOnTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        WebElement shoppingCartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test=\"shopping-cart-badge\"]")));
        //get text from the shopping cart badge (number of item)
        String itemCountText = shoppingCartBadge.getText();
        //convert the text to an integer for comparison
        int itemCount = Integer.parseInt(itemCountText);
        int expectedItemCount = 1; // amount of item expected to be
        //assert that the actual count matches the expected amount
        Assert.assertEquals(expectedItemCount , itemCount);
    }


    @When("I remove the product from the cart")
    public void iRemoveTheProductFromTheCart() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("the 'Remove' button should change back to an 'Add to cart' button")
    public void theRemoveButtonShouldChangeBackToAnAddToCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack")));
        //check if the button change to 'Add to cart'
        WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Assert.assertTrue(addButton.isDisplayed());
    }
}
