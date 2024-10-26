package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Checkout_Steps {

    WebDriver driver = Hooks.getDriver();

    @Given("I am logged into the Swag Labs website with a product added to the cart and am on the cart page")
    public void iAmLoggedIntoTheSwagLabsWebsiteWithAProductAddedToTheCartAndAmOnTheCartPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=\"Login\"]")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.cssSelector("[data-test=\"shopping-cart-link\"]")).click();
    }

    @When("I click the 'Checkout' button")
    public void iClickTheCheckoutButton() {
        driver.findElement(By.id("checkout")).click();
    }

    @Then("I will be redirect to checkout page")
    public void iWillBeRedirectToCheckoutPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
        String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    }

    @When("I enter my first name")
    public void iEnterMyFirstName() {
        driver.findElement(By.name("firstName")).sendKeys("John");
    }

    @And("I enter my last name")
    public void iEnterMyLastName() {
        driver.findElement(By.name("lastName")).sendKeys("Smith");
    }

    @And("I enter my postal code")
    public void iEnterMyPostalCode() {
        driver.findElement(By.name("postalCode")).sendKeys("12345");
    }

    @And("I click 'Continue' button")
    public void iClickContinueButton() {
        driver.findElement(By.name("continue")).click();
    }

    @Then("I will be redirect to Checkout:Overview page")
    public void iWillBeRedirectToCheckoutOverviewPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));
        String expectedURL = "https://www.saucedemo.com/checkout-step-two.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    }

    @And("I should see the cart list and summary info")
    public void iShouldSeeTheCartListAndSummaryInfo() {
    }

    @When("I click the 'Finish' button")
    public void iClickTheFinishButton() {
        driver.findElement(By.name("finish")).click();
    }

    @Then("I should be redirect to the Checkout:Complete page")
    public void iShouldBeRedirectToTheCheckoutCompletePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("/checkout-complete.html"));
        String expectedURL = "https://www.saucedemo.com/checkout-complete.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    }

    @And("I should be presented with a successful order submission message")
    public void iShouldBePresentedWithASuccessfulOrderSubmissionMessage() {
        WebElement submission_message = driver.findElement(By.xpath("//div[@id='checkout_complete_container']/h2[@class='complete-header']"));
        WebElement message = driver.findElement(By.xpath("//div[@id='checkout_complete_container']/div[@class='complete-text']"));
        Assert.assertEquals(submission_message.getText(), "Thank you for your order!");
        Assert.assertEquals(message.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @When("I click the 'Back Home' button")
    public void iClickTheBackHomeButton() {
        driver.findElement(By.name("back-to-products")).click();
    }

    @Then("I should be redirect to inventory page")
    public void iShouldBeRedirectToInventoryPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    }
}
