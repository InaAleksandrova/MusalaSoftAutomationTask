import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompanyPage_FacebookPageTest extends BaseTest {

    @Test
    public void verifyFacebookPageOpensCorrectly() {
        CompanyPage companyPage = new CompanyPage(driver);
        companyPage.clickCompanyLink();
        Assert.assertEquals(companyPage.getPageUrl(), "https://www.musala.com/company/", "Incorrect URL");
        Assert.assertTrue(companyPage.isLeaderShipSectionDisplayed(), "The leadership section is not displayed");
        companyPage.clickFacebookLink();

        companyPage.switchToFacebookPage();
        Assert.assertEquals(companyPage.getPageUrl(), "https://www.facebook.com/MusalaSoft?fref=ts", "Incorrect URL");
        companyPage.acceptAllFacebookCookies();
        companyPage.clickFaceBookProfilePicture();
        Assert.assertTrue(companyPage.verifyMusalaProfilePictureIsDisplayed(), "The profile picture is not displayed");
    }


}
