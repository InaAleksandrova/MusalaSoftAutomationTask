import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BasePage {

    private static WebDriver driver;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static String readFile(String locatorName, String propFile) {
        Properties properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(String.format("%s\\src\\main\\resources\\%s", System.getProperty("user.dir"), propFile));
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(locatorName);
    }


}
