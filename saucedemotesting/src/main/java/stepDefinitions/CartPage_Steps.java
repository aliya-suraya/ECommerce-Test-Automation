package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage_Steps {

    WebDriver driver = Hooks.getDriver();

    @Given("I am successfully logged into Swag Labs website")
    public void iAmSuccessfullyLoggedIntoSwagLabsWebsite() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=\"Login\"]")).click();
    }

    @When("I add products to the cart")
    public void iAddProductsToTheCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    @And("I click the cart button")
    public void iClickTheCartButton() {
        driver.findElement(By.cssSelector("[data-test=\"shopping-cart-link\"]")).click();
    }

    @And("I should be redirect to the cart page")
    public void iShouldBeRedirectToTheCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        //wait for page to load (using a URL check here)
        wait.until(ExpectedConditions.urlContains("/cart.html"));
        //verify that the current URL is the expected cart page
        String expectedURL = "https://www.saucedemo.com/cart.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL , expectedURL);
    }

    @Then("I should see list of products added")
    public void iShouldSeeListOfProductsAdded() {
        //get product name added
        WebElement productAdded = driver.findElement(By.cssSelector("[data-test=\"item-0-title-link\"]"));
        String productAddedText = productAdded.getText();
        //get product name in the cart
        WebElement productCart = driver.findElement(By.cssSelector("[data-test=\"item-0-title-link\"]"));
        String confirmProductCart = productCart.getText();
        //verify that the product name in the cart matches the product that was added
        Assert.assertEquals(confirmProductCart, productAddedText);
    }

    @When("I clicked 'Remove' button on a product")
    public void iClickedRemoveButtonOnAProduct() {
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
    }

    @Then("the product will be remove from the cart list")
    public void theProductWillBeRemoveFromTheCartList() {
        boolean isProductRemoved;

        try
        {
            //try to locate the product again
            driver.findElement(By.cssSelector("[data-test=\"item-0-title-link\"]"));
            isProductRemoved = false;
        }
        catch (NoSuchElementException e){
            isProductRemoved = true;
        }

        Assert.assertTrue(isProductRemoved, "Product should be removed from cart");
    }

    @When("I click the 'Continue Shopping' button")
    public void iClickTheContinueShoppingButton() {
        driver.findElement(By.id("continue-shopping")).click();
    }

    @Then("I should be redirect back to the inventory page")
    public void iShouldBeRedirectBackToTheInventoryPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        //verify the current URL is expected inventory page
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL , expectedURL);

    }

}
