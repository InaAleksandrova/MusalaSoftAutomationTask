import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CareersPage {

    private final WebDriver driver;
    private final String PROP_FILE = "Careers.properties";

    private final String CAREERS_LINK = BasePage.readFile("careersLink", PROP_FILE);
    private final String BUTTON_CHECK_OUR_POSITIONS = BasePage.readFile("buttonCheckOurPositions", PROP_FILE);
    private final String DROPDOWN_SELECT_LOCATION = BasePage.readFile("dropdownSelectLocation", PROP_FILE);
    private final String POSITION_NAMES = BasePage.readFile("positionNames", PROP_FILE);
    private final String POSITION_LINKS = BasePage.readFile("positionLinks", PROP_FILE);
    private final String SECTION_GENERAL = BasePage.readFile("sectionGeneralDescription", PROP_FILE);
    private final String SECTION_REQUIREMENTS = BasePage.readFile("sectionRequirements", PROP_FILE);
    private final String SECTION_RESPONSIBILITIES = BasePage.readFile("sectionResponsibilities", PROP_FILE);
    private final String SECTION_WHAT_WE_OFFER = BasePage.readFile("sectionWhatWeOffer", PROP_FILE);
    private final String BUTTON_APPLY = BasePage.readFile("buttonApply", PROP_FILE);


    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean getTitle(String title) {
        return driver.getTitle().contains(title);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateToCareersPage() {
        driver.findElement(By.xpath(CAREERS_LINK)).click();
    }

    public void clickCheckOurPositionsButton() {
        driver.findElement(By.xpath(BUTTON_CHECK_OUR_POSITIONS)).click();
    }

    public void selectLocation(String location) {
        Select locations = new Select(driver.findElement(By.xpath(DROPDOWN_SELECT_LOCATION)));
        locations.selectByValue(location);
    }

    //Iterate through the position, if the one passed in the test in not visible, scroll to it
    // and then click on it; break the loop
    public void choosePositionByName(String positionName) {
        List<WebElement> positions = driver.findElements(By.xpath(POSITION_NAMES));
        for (WebElement position : positions) {
            if (!position.isDisplayed()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", position);
            }
            if (position.getText().equals(positionName)) {
                position.click();
                break;
            }
        }
    }

    public boolean isGeneralDescriptionSectionDisplayed() {
        return driver.findElement(By.xpath(SECTION_GENERAL)).isDisplayed();
    }

    public boolean isRequirementsSectionDisplayed() {
        return driver.findElement(By.xpath(SECTION_REQUIREMENTS)).isDisplayed();
    }

    public boolean isResponsibilitiesSectionDisplayed() {
        return driver.findElement(By.xpath(SECTION_RESPONSIBILITIES)).isDisplayed();
    }

    public boolean isWhatWeOfferSectionDisplayed() {
        return driver.findElement(By.xpath(SECTION_WHAT_WE_OFFER)).isDisplayed();
    }

    public boolean verifyApplyButtonIsDisplayed() {
        return driver.findElement(By.xpath(BUTTON_APPLY)).isEnabled();
    }

    //Cannot make it work with driver.click; this a workaround
    public void clickApplyButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(BUTTON_APPLY)));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(BUTTON_APPLY))));
        js.executeScript("arguments[0].click()", driver.findElement(By.xpath(BUTTON_APPLY)));
    }


    public void printAllPositionNamesAndLinks() {
        List<WebElement> positions = driver.findElements(By.xpath(POSITION_NAMES));
        List<WebElement> positionLinks = driver.findElements(By.xpath(POSITION_LINKS));
        for (int i = 0; i < positions.size(); i++) {
            String positionName = positions.get(i).getText();
            String positionLink = positionLinks.get(i).getAttribute("href");
            System.out.println("Position: " + positionName);
            System.out.println("More info: " + positionLink);
        }
    }
}
