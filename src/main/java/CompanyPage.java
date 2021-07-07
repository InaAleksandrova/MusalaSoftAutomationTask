import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CompanyPage {

    private final WebDriver driver;
    private final String PROP_FILE = "CompanyPage.properties";

    private final String COMPANY_LINK = BasePage.readFile("companyLink", PROP_FILE);
    private final String LEADERSHIP_SECTION = BasePage.readFile("leadershipSection", PROP_FILE);
    private final String FACEBOOK_LINK = BasePage.readFile("facebookLink", PROP_FILE);
    private final String FACEBOOK_ACCEPT_COOKIES = BasePage.readFile("facebookAcceptCookies", PROP_FILE);
    private final String FACEBOOK_PROFILE_PICTURE = BasePage.readFile("facebookProfilePicture", PROP_FILE);
    private final String FACEBOOK_PROFILE_PICTURE_FULL = BasePage.readFile("facebookProfilePictureFull", PROP_FILE);


    public CompanyPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCompanyLink() {
        driver.findElement(By.xpath(COMPANY_LINK)).click();
    }

    public boolean isLeaderShipSectionDisplayed() {
        return driver.findElement(By.xpath(LEADERSHIP_SECTION)).isDisplayed();
    }

    public void clickFacebookLink() {
        driver.findElement(By.xpath(FACEBOOK_LINK)).click();
    }


    //Using iterator to switch to the Facebook page tab
    public void switchToFacebookPage() {
        Set<String> windowIds = driver.getWindowHandles();
        Iterator<String> iterator = windowIds.iterator();
        iterator.next();
        driver.switchTo().window(iterator.next());
    }

    public void acceptAllFacebookCookies() {
        driver.findElement(By.xpath(FACEBOOK_ACCEPT_COOKIES)).click();
    }

    public void clickFaceBookProfilePicture() {
        driver.findElement(By.xpath(FACEBOOK_PROFILE_PICTURE)).click();
    }

    public boolean verifyMusalaProfilePictureIsDisplayed() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.xpath(FACEBOOK_PROFILE_PICTURE_FULL)).isDisplayed();
    }
}
