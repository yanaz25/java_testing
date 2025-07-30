import constants.TestConstants;
import org.testng.annotations.Test;
import pages.CardsPage;
import pages.HomePage;
import pages.forms.AvatarInterestForm;
import pages.forms.PersonalDetailsForm;
import pages.forms.RegistrationForm;
import utilities.RandomUtils;

import static org.testng.Assert.*;

public class UserInterfaceTests extends BaseTest {

    @Test
    public void registrationTest() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().isDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, homePage.getName()));

        RegistrationForm registrationForm = new RegistrationForm();
        homePage.clickNextPageLink();
        CardsPage cardsPage = new CardsPage();
        assertTrue(cardsPage.state().waitForDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, cardsPage.getName()));

        char formIndicator = cardsPage.getFormIndicator();
        assertEquals(formIndicator, TestConstants.REG_FORM_IND, TestConstants.CARD_ASSERTION);

        String email = RandomUtils.generateRandomString(TestConstants.EMAIL_LENGTH);
        String password = RandomUtils.generatePassword(email, TestConstants.PASSWORD_LENGTH);
        String domain = RandomUtils.generateRandomString(TestConstants.DOMAIN_LENGTH);

        registrationForm.typePassword(password);
        registrationForm.typeEmail(email);
        registrationForm.typeDomain(domain);
        registrationForm.setDomain();
        registrationForm.clickAcceptTermsCheckbox();
        registrationForm.clickNext();

        AvatarInterestForm avatarInterestForm = new AvatarInterestForm();
        assertTrue(avatarInterestForm.state().waitForDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, avatarInterestForm.getName()));

        formIndicator = cardsPage.getFormIndicator();
        assertEquals(formIndicator, TestConstants.INTEREST_FORM_IND, TestConstants.CARD_ASSERTION);

        avatarInterestForm.uploadFile(TestConstants.PATH_TO_IMG);
        avatarInterestForm.selectCheckBoxesByCount(TestConstants.CHECK_BOX_COUNT);
        avatarInterestForm.clickNext();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        assertTrue(personalDetailsForm.state().waitForDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, personalDetailsForm.getName()));

        formIndicator = cardsPage.getFormIndicator();
        assertEquals(formIndicator, TestConstants.PROFILE_FORM_IND, TestConstants.CARD_ASSERTION);
    }

    @Test
    public void hideHelpFormTest() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().isDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, homePage.getName()));

        homePage.clickNextPageLink();
        CardsPage cardsPage = new CardsPage();
        assertTrue(cardsPage.state().waitForDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, cardsPage.getName()));

        int formIndicator = cardsPage.getFormIndicator();
        assertEquals(formIndicator, TestConstants.REG_FORM_IND, TestConstants.CARD_ASSERTION);

        cardsPage.hideHelpForm();
        assertTrue(cardsPage.isHelpFormNotDisplayed(), "help form is displayed!");
    }

    @Test
    public void acceptCookieTest() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().isDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, homePage.getName()));

        homePage.clickNextPageLink();
        CardsPage cardsPage = new CardsPage();
        assertTrue(cardsPage.state().waitForDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, cardsPage.getName()));

        int formIndicator = cardsPage.getFormIndicator();
        assertEquals(formIndicator, TestConstants.REG_FORM_IND, TestConstants.CARD_ASSERTION);

        cardsPage.acceptCookie();
        assertTrue(cardsPage.isCookieNotDisplayed(), "cookie is displayed");
    }

    @Test
    public void timerTest() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().isDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, homePage.getName()));

        homePage.clickNextPageLink();
        CardsPage cardsPage = new CardsPage();
        assertTrue(cardsPage.state().waitForDisplayed(), String.format(TestConstants.DISPLAY_ASSERTION, cardsPage.getName()));

        int formIndicator = cardsPage.getFormIndicator();
        assertEquals(formIndicator, TestConstants.REG_FORM_IND, TestConstants.CARD_ASSERTION);

        String timerValue = cardsPage.getTimerValue();
        assertTrue(timerValue.contains(TestConstants.TIMER_VALUE), "timer doesn't start from " + TestConstants.TIMER_VALUE);
    }
}


