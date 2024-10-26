package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Steps {

    WebDriver driver = Hooks.getDriver();

    @Given("I access the Swag Labs login page")
    public void iAccessTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
//        Thread.sleep(5000);
    }
    @When("I enter one of the username given")
    public void iEnterOneOfTheUsernameGiven() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
//        Thread.sleep(5000);
    }
    @And ("I enter a password")
    public void iEnterAPassword() {
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }
    @And ("I click on the login button")
    public void iClickOnTheLoginButton() {
        driver.findElement(By.cssSelector("[value=\"Login\"]")).click();

    }
    @Then ("I should be successfully login and redirect to inventory page")
    public void iShouldBeSuccessfulLoginAndRedirectToInventoryPpage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }
}
