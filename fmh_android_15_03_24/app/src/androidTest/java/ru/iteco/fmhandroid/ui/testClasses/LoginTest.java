package ru.iteco.fmhandroid.ui.testClasses;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.EspressoIdlingResources;
import ru.iteco.fmhandroid.ui.Utils;
import ru.iteco.fmhandroid.ui.pageObjects.TopBar;
import ru.iteco.fmhandroid.ui.pageObjects.LoginScreen;
import ru.iteco.fmhandroid.ui.pageObjects.MainScreen;
import ru.iteco.fmhandroid.ui.pageObjects.ToastErrorMessages;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoginTest {
    LoginScreen loginScreen = new LoginScreen();
    TopBar topBar = new TopBar();
    MainScreen mainScreen = new MainScreen();
    ToastErrorMessages toastErrors = new ToastErrorMessages();

    private final String validLogin = "login2";
    private final String validPassword = "password2";

    private final String inValidLogin = "login4234";
    private final String inValidPassword = "password24323423";

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(topBar.getTopBarFragmentMain(), 20000));
        if (mainScreen.isDisplayedButtonProfile()) {
            topBar.logOut();
        }
    }

    @Description("Test case ID - 1 Successful login")
    @Test
    public void successfulLogin() {
        loginScreen.visibilityElement();
        loginScreen.inputInFieldLogin(validLogin);
        loginScreen.inputInFieldPassword(validPassword);
        loginScreen.pressButton();
        mainScreen.isDisplayedButtonProfile();
    }

    @Description("Test case ID - 7.1 Unsuccessful login with empty login fields")
    @Test
    public void unsuccessfulLoginWithEmptyFields() {
        loginScreen.pressButton();
        loginScreen.visibilityElement();
        toastErrors.checkToastErrorWhenEmptyLoginFields();
    }

    @Description("Test case ID - 7.2 Unsuccessful login with uppercase valid user data")
    @Test
    public void unsuccessfulLoginWithUppercaseLoginData() {
        loginScreen.inputInFieldLogin(validLogin.toUpperCase());
        loginScreen.inputInFieldPassword(validPassword.toUpperCase());
        loginScreen.pressButton();
        loginScreen.visibilityElement();
        toastErrors.checkToastErrorWhenInvalidUserData();
    }

    @Description("Test case ID - 7.3 Unsuccessful login with bad user data")
    @Test
    public void unsuccessfulLogin() {
        loginScreen.inputInFieldLogin(inValidLogin);
        loginScreen.inputInFieldPassword(inValidPassword);
        loginScreen.pressButton();
        loginScreen.visibilityElement();
        toastErrors.checkToastErrorWhenInvalidUserData();
    }

    @Description("Test case ID - 20 Checking of text of error message when unsuccessful login")
    @Test
    public void unsuccessfulLoginTextMessageCheck() {
        loginScreen.inputInFieldLogin(inValidLogin);
        loginScreen.inputInFieldPassword(inValidPassword);
        loginScreen.pressButton();
        loginScreen.visibilityElement();
        toastErrors.checkToastErrorText("Неверный логин или пароль");
    }
}
