package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    private String readEnvProperties(String envName) {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(String.format("%s\\src\\main\\resources\\Environment.properties", System.getProperty("user.dir")));
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(envName);
    }

    public WebDriver initializeDriver() {
        String browserName = readEnvProperties("browser");

        String browserPath = String.format("%s\\src\\main\\resources\\Drivers\\", System.getProperty("user.dir"));

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", String.format("%schromedriver.exe", browserPath));
            driver = new ChromeDriver();
        } else if(browserName.equals("firefox")) {
            System.setProperty("webdriver.firefox.driver", String.format("%sgeckodriver.exe", browserPath));
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }


    @BeforeTest
    public void setUp() {
        String url = readEnvProperties("url");
        driver = initializeDriver();
        driver.get(url);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

}
