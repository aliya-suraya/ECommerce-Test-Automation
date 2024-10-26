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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter_Steps {

    WebDriver driver = Hooks.getDriver();

    @Given("I login into Swag Labs website successfully")
    public void iLoginIntoSwagLabsWebsiteSuccessfully() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=\"Login\"]")).click();
    }

    @When("I am on the Swag Labs inventory page")
    public void iAmOnTheSwagLabsInventoryPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @And("I click the filter menu")
    public void iClickTheFilterMenu() {
        driver.findElement(By.cssSelector("[class='select_container']")).click();
    }

    @And("I select the filter option Name \\(A to Z)")
    public void iSelectTheFilterOptionNameAToZ() {
        driver.findElement(By.xpath("//option[@value='az']")).click();
    }

    @Then("I should see the product list sorted in alphabetical order \\(A to Z)")
    public void iShouldSeeTheProductListSortedInAlphabeticalOrderAToZ() {
        //get current list of product
        //check if the list is in alphabetical order
        
        boolean productOrder = true;

        List<WebElement> productElement = driver.findElements(By.cssSelector("[data-test='inventory-list']"));
        List<String> productName = new ArrayList<>();

        for (int i = 0; i < productElement.size(); i++) {
            WebElement productElements = productElement.get(i);
            String productNames = productElements.getText();
            productName.add(productNames);
        }

        //>0 means not sorted alphabetically
        //check for ascending order (A to Z)
            for (int i = 0; i < productName.size() - 1; i++) {
                if (productName.get(i).compareTo(productName.get(i + 1)) > 0) {
                    productOrder = false;
                    break;
                }
            }

        Assert.assertTrue(productOrder, "Product should be in ascending order");
    }


    @When("I select the filter option Name \\(Z to A)")
    public void iSelectTheFilterOptionNameZToA() {
        driver.findElement(By.cssSelector("[class='select_container']")).click();
        driver.findElement(By.xpath("//option[@value='za']")).click();
    }

    @Then("I should see the product list sorted in reverse alphabetical order \\(Z to A)")
    public void iShouldSeeTheProductListSortedInReverseAlphabeticalOrderZToA(){
        //get current list of product
        //check if the list is in order

        boolean productOrder = true;

        List<WebElement> productElement = driver.findElements(By.cssSelector("[data-test='inventory-list']"));
        List<String> productName = new ArrayList<>();

        for (int i = 0; i < productElement.size(); i++) {
            WebElement productElements = productElement.get(i);
            String productNames = productElements.getText();
            productName.add(productNames);
        }

            for (int i = 0; i < productName.size() - 1; i++) {
                if (productName.get(i).compareTo(productName.get(i + 1)) < 0) {
                    productOrder = false;
                    break;
                }
            }

        Assert.assertTrue(productOrder, "Product should be in descending order");
    }

    @When("I select the filter option Price \\(low to high)")
    public void iSelectTheFilterOptionPriceLowToHigh() {
        driver.findElement(By.cssSelector("[class='select_container']")).click();
        driver.findElement(By.xpath("//option[@value='lohi']")).click();
    }

    @Then("I should see the product list sorted by price from low to high")
    public void iShouldSeeTheProductListSortedByPriceFromLowToHigh() {
        //select first and second price of product in the list and compare
        //if the first product price is lower than the second product then the order is right
        //and vice versa

        boolean productOrder = true;
        List<WebElement> productPrice = driver.findElements(By.cssSelector("[class=\"inventory_item_price\"]"));

        for (int i = 1; i < productPrice.size(); i++) {
            String price = productPrice.get(i-1 ).getText().replace("$","").trim();
            String price2 = productPrice.get(i).getText().replace("$","").trim();

            double numPrice = Double.parseDouble(price);
            double numPrice2 = Double.parseDouble(price2);

            if (numPrice > numPrice2) {
                productOrder = false;
            }
        }
        Assert.assertTrue(productOrder, "Product price should be from lower to higher");
    }

    @When("I select the filter option Price \\(high to low)")
    public void iSelectTheFilterOptionPriceHighToLow() {
        driver.findElement(By.cssSelector("[class='select_container']")).click();
        driver.findElement(By.xpath("//option[@value='hilo']")).click();
    }

    @Then("I should see the product list sorted by price from high to low")
    public void iShouldSeeTheProductListSortedByPriceFromHighToLow() {
        boolean productOrder = true;
        List<WebElement> productPrice = driver.findElements(By.cssSelector("[class=\"inventory_item_price\"]"));

        for (int i = 1; i < productPrice.size(); i++) {
            String price = productPrice.get(i-1 ).getText().replace("$","").trim();
            String price2 = productPrice.get(i).getText().replace("$","").trim();

            double numPrice = Double.parseDouble(price);
            double numPrice2 = Double.parseDouble(price2);

            if (numPrice < numPrice2) {
                productOrder = false;
            }
        }
        Assert.assertTrue(productOrder, "Product price should be from lower to higher");
    }
}
