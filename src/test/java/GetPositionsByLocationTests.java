import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetPositionsByLocationTests extends BaseTest {

    @Test(dataProvider = "location")
    public void listPositionsByLocation(String location) {
        CareersPage careersPage = new CareersPage(driver);
        careersPage.navigateToCareersPage();
        careersPage.clickCheckOurPositionsButton();
        Assert.assertEquals(careersPage.getPageUrl(), "https://www.musala.com/careers/join-us/", "Incorrect URL");
        careersPage.selectLocation(location);
        System.out.println(location);
        careersPage.printAllPositionNamesAndLinks();
    }

    @DataProvider(name = "location")
    public Object[][] getLocation() {
        return new Object[][] {
                {"Sofia"},
                {"Skopje"},
        };
    }
}
