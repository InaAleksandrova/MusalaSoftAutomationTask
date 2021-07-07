import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class ContactForm {

    private final WebDriver driver;
    private final String PROP_FILE = "ContactForm.properties";

    private final String BUTTON_CONTACT_US = BasePage.readFile("buttonContactUs", PROP_FILE);
    private final String FORM_CONTACT = BasePage.readFile("formContactUs", PROP_FILE);
    private final String INPUT_FIELD_NAME = BasePage.readFile("inputFieldName", PROP_FILE);
    private final String INPUT_FIELD_EMAIL = BasePage.readFile("inputFieldEmail", PROP_FILE);
    private final String INPUT_FIELD_MOBILE = BasePage.readFile("inputFieldMobile", PROP_FILE);
    private final String INPUT_FIELD_SUBJECT = BasePage.readFile("inputFieldSubject", PROP_FILE);
    private final String INPUT_FIELD_MESSAGE = BasePage.readFile("inputFieldMessage", PROP_FILE);
    private final String BUTTON_SEND_CONTACT_FORM = BasePage.readFile("buttonSendContactForm", PROP_FILE);
    private final String ERROR_MESSAGE_INVALID_EMPTY_EMAIL = BasePage.readFile("errorMessageInvalidEmptyEmail", PROP_FILE);
    private final String ERROR_MESSAGE_INVALID_EMPTY_NAME = BasePage.readFile("errorMessageInvalidEmptyName", PROP_FILE);
    private final String ERROR_MESSAGE_INVALID_EMPTY_MOBILE_NUMBER = BasePage.readFile("errorMessageInvalidEmptyMobileNumber", PROP_FILE);
    private final String BUTTON_CLOSE_FORM = BasePage.readFile("buttonCloseFrom", PROP_FILE);
    private final String INPUT_UPLOAD_FILE = BasePage.readFile("inputUploadFile", PROP_FILE);
    private final String ERROR_POP_UP = BasePage.readFile("errorPopUp", PROP_FILE);
    private final String ERROR_POP_UP_CLOSE_BUTTON = BasePage.readFile("errorPopUpCloseButton", PROP_FILE);


    public ContactForm(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContactUsButton() {
        driver.findElement(By.xpath(BUTTON_CONTACT_US)).click();
    }

    public boolean verifyContactFormIsVisible() {
        return driver.findElement(By.xpath(FORM_CONTACT)).isDisplayed();
    }

    public void enterName(String name) {
        WebElement nameInput = driver.findElement(By.xpath(INPUT_FIELD_NAME));
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterEmail(String email) {
       WebElement emailInput = driver.findElement(By.xpath(INPUT_FIELD_EMAIL));
       emailInput.clear();
       emailInput.sendKeys(email);
    }

    public void enterSubject(String subject) {
        WebElement inputSubject = driver.findElement(By.xpath(INPUT_FIELD_SUBJECT));
        inputSubject.clear();
        inputSubject.sendKeys(subject);
    }

    public void enterMessage(String message) {
        WebElement inputMessage = driver.findElement(By.xpath(INPUT_FIELD_MESSAGE));
        inputMessage.clear();
        inputMessage.sendKeys(message);
    }

    public String getInvalidEmptyEmailText() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.xpath(ERROR_MESSAGE_INVALID_EMPTY_EMAIL)).getText();
    }

    public String getInvalidEmptyNameText() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.xpath(ERROR_MESSAGE_INVALID_EMPTY_NAME)).getText();
    }

    public String getInvalidEmptyMobileNumberText() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.xpath(ERROR_MESSAGE_INVALID_EMPTY_MOBILE_NUMBER)).getText();
    }

    public void clickCloseFormButton() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath(BUTTON_CLOSE_FORM)).click();
    }

    public void enterMobile(String mobile) {
        driver.findElement(By.xpath(INPUT_FIELD_MOBILE)).sendKeys(mobile);
    }

    public void uploadFile(String filePath) {
        driver.findElement(By.xpath(INPUT_UPLOAD_FILE)).sendKeys(filePath);
    }

    //Cannot make it work with the standard driver.click; this a workaround
    public void clickSendButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(By.xpath(BUTTON_SEND_CONTACT_FORM)));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //this.isErrorPopUpDisplayed();
    }

    private void isErrorPopUpDisplayed() {
        if(driver.findElement(By.xpath(ERROR_POP_UP)).isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", driver.findElement(By.xpath(ERROR_POP_UP_CLOSE_BUTTON)));
            driver.findElement(By.xpath(ERROR_POP_UP_CLOSE_BUTTON)).click();
        }
    }

}
