import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import play.Play;
import play.libs.F.Callback;
import play.test.TestBrowser;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;
import static play.test.Helpers.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(9000, fakeApplication(inMemoryDatabase())), PhantomJSDriver.class, new Callback<TestBrowser>() {
            public void invoke(final TestBrowser browser) {
                String userName = Play.application().configuration().getString("userName");
                String password = Play.application().configuration().getString("password");
                if (StringUtils.isBlank(userName)) fail("Username not set");
                if (StringUtils.isBlank(password)) fail("Password not set");

                browser.goTo("http://localhost:9000");
                assertThat(browser.pageSource()).contains("Runkeeper Client Test");

                browser.$("#login").click();
                signInToRunkeeper(browser, userName, password);
                browser.pageSource().contains("userID");
            }
        });
    }

    private void signInToRunkeeper(TestBrowser browser, String userName, String password) {
        browser.$("#emailInput").text(userName);
        browser.$("#passwordInput").text(password);
        browser.executeScript("document.forms[1].submit();");
    }

}
