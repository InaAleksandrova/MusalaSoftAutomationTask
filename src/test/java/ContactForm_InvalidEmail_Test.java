import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactForm_InvalidEmail_Test extends BaseTest {

    @Test(dataProvider = "invalidEmails")
    public void enterInvalidEmail(String email) throws InterruptedException {
        ContactForm contactForm = new ContactForm(driver);
        contactForm.clickContactUsButton();
        Assert.assertTrue(contactForm.verifyContactFormIsVisible(), "The contact form is not displayed");
        contactForm.enterName("Test");
        contactForm.enterEmail(email);
        contactForm.enterSubject("Test");
        contactForm.enterMessage("Some text");
        contactForm.clickSendButton();
        Assert.assertEquals(contactForm.getInvalidEmptyEmailText(), "The e-mail address entered is invalid.");
        contactForm.clickCloseFormButton();
        driver.navigate().refresh();
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] addEmail() {
        return new Object[][] {
                {"test@test"},
                {"test.com"},
                {"test@test..com"},
                {"test test@com"},
                {"test//test.com"}
        };
    }



}
