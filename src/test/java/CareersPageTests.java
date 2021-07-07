import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CareersPageTests extends BaseTest {

    @Test
    public void choosePosition() {
        CareersPage careersPage = new CareersPage(driver);
        careersPage.navigateToCareersPage();
        careersPage.clickCheckOurPositionsButton();
        Assert.assertEquals(careersPage.getPageUrl(), "https://www.musala.com/careers/join-us/", "Incorrect URL");
        careersPage.selectLocation("Anywhere");
        careersPage.choosePositionByName("Experienced Automation QA Engineer");
        Assert.assertTrue(careersPage.getTitle("Automation QA Engineer"), "Incorrect page title");
        Assert.assertTrue(careersPage.isGeneralDescriptionSectionDisplayed(), "General description section is not displayed");
        Assert.assertTrue(careersPage.isRequirementsSectionDisplayed(), "Requirements section is not displayed");
        Assert.assertTrue(careersPage.isResponsibilitiesSectionDisplayed(), "Responsibilities section is not displayed");
        Assert.assertTrue(careersPage.isWhatWeOfferSectionDisplayed(), "What we offer section is not displayed");
        Assert.assertTrue(careersPage.verifyApplyButtonIsDisplayed(), "Apply button is not displayed");
        careersPage.clickApplyButton();
    }

    @Test(dependsOnMethods = {"choosePosition"})
    public void enterInvalidValuesInForm() {
        ContactForm contactForm = new ContactForm(driver);
        Assert.assertTrue(contactForm.verifyContactFormIsVisible(), "The application form is not displayed");
        contactForm.enterName("");
        contactForm.enterEmail("test@test");
        contactForm.enterMobile("");
        contactForm.uploadFile("");
        contactForm.clickSendButton();
        Assert.assertEquals(contactForm.getInvalidEmptyNameText(), "The field is required.");
        Assert.assertEquals(contactForm.getInvalidEmptyEmailText(), "The e-mail address entered is invalid.");
        Assert.assertEquals(contactForm.getInvalidEmptyMobileNumberText(), "The field is required.");
    }
}
