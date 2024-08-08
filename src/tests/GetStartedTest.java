package tests;

import lib.CoreTestCase;
import lib.ui.WeclomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThrouhWelcome()
    {
        if (this.Platform.isAndroid())
        {
            return;
        }

        WeclomePageObject WelcomePage = new WeclomePageObject(driver);

        WelcomePage.waitForLerningMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDateCollectedText();
        WelcomePage.clickGetStartedButton();

    }
}
