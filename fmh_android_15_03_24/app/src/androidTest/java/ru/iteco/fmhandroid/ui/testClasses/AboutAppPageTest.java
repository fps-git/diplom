package ru.iteco.fmhandroid.ui.testClasses;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.pageObjects.AboutAppPage;
import ru.iteco.fmhandroid.ui.pageObjects.TopBar;
import ru.iteco.fmhandroid.ui.pageObjects.LoginScreen;
import ru.iteco.fmhandroid.ui.pageObjects.MainScreen;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutAppPageTest {
    LoginScreen loginScreen = new LoginScreen();
    TopBar topBar = new TopBar();
    MainScreen mainScreen = new MainScreen();

    AboutAppPage aboutAppPage = new AboutAppPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(Utils.waitDisplayed(topBar.getTopBarFragmentMain(), 20000));
        if (!mainScreen.isDisplayedButtonProfile()) {
            loginScreen.successfulAuthorization();
        }
    }

    @Description("Test case ID - 6.5 About App page check")
    @Test
    public void policyTextAndLinkTest() {
        topBar.AboutApp();
        aboutAppPage.policyTextCheck("Политика конфиденциальности:");
        aboutAppPage.policyLinkCheck("https://vhospice.org/#/privacy-policy");
        aboutAppPage.back();
        mainScreen.lastNews.check(matches(isDisplayed()));
    }

    @Test
    public void termsTextAndLinkTest() {
        topBar.AboutApp();
        aboutAppPage.termsTextCheck("Пользовательское соглашение:");
        aboutAppPage.termsLinkCheck("https://vhospice.org/#/terms-of-use");
        aboutAppPage.back();
        mainScreen.lastNews.check(matches(isDisplayed()));
    }
}
