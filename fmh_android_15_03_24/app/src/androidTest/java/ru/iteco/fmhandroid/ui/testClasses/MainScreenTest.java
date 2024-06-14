package ru.iteco.fmhandroid.ui.testClasses;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Utils;
import ru.iteco.fmhandroid.ui.pageObjects.TopBar;
import ru.iteco.fmhandroid.ui.pageObjects.LoginScreen;
import ru.iteco.fmhandroid.ui.pageObjects.MainScreen;
import ru.iteco.fmhandroid.ui.pageObjects.NewsPage;
import ru.iteco.fmhandroid.ui.pageObjects.OurMission;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainScreenTest {
    LoginScreen loginScreen = new LoginScreen();
    TopBar topBar = new TopBar();
    MainScreen mainScreen = new MainScreen();
    NewsPage newsPage = new NewsPage();
    OurMission ourMission = new OurMission();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(Utils.waitDisplayed(topBar.getTopBarFragmentMain(), 20000));
        if (!mainScreen.isDisplayedButtonProfile()) {
            loginScreen.successfulAuthorization();
        }
    }

    @Description("Test case ID - 2.1 Burger menu test")
    @Test
    public void openBurgerMenu() {
        topBar.buttonMainMenu.perform(click());
        topBar.mainMenuMain.check(matches(isDisplayed()));
        topBar.mainMenuNews.check(matches(isDisplayed()));
        topBar.mainMenuAboutApp.check(matches(isDisplayed()));
    }

    @Description("Test case ID - 2.2 App logo test")
    @Test
    public void appLogoTest() {
        topBar.topBarLogo.check(matches(isDisplayed()));
    }

    @Description("Test case ID - 2.3 Our Mission button test")
    @Test
    public void ourMissionButtonTest() {
        topBar.buttonOurMission.check(matches(isDisplayed())).perform(click());
        ourMission.title.check(matches(isDisplayed()));
        topBar.buttonMainMenu.perform(click());
        topBar.mainMenuMain.perform(click());
    }

    @Description("Test case ID - 2.4 Profile button test")
    @Test
    public void profileButtonTest() {
        topBar.profileButton.perform(click());
        onView(withText("Выйти")).check(matches(isDisplayed()));
    }

    @Description("Test case ID - 2.5 Last news block test")
    @Test
    public void lastNewsBlockTest() {
        mainScreen.lastNews.check(matches(isDisplayed()));
        mainScreen.expandButton.check(matches(isDisplayed()));
        mainScreen.expandButton.perform(click());
        mainScreen.allNews.check(matches(not(isDisplayed())));
    }

    @Description("Test case ID - 2.6 All news button test")
    @Test
    public void allNewsButtonTest() {
        mainScreen.allNews.check(matches(isDisplayed()));
        mainScreen.allNews.perform(click());
        newsPage.checkNews();
        topBar.buttonMainMenu.perform(click());
        topBar.mainMenuMain.perform(click());
    }

    @Description("Test case ID - 24.3 Open news page by burger menu")
    @Test
    public void openNewsPage() {
        topBar.switchToNews();
        newsPage.checkNews();
        topBar.mainScreen();
    }

    @Description("Test case ID - 6.5 Open 'About' screen")
    @Test
    public void openPageAboutApplication() {
        topBar.AboutApp();
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_version_value_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_privacy_policy_label_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_terms_of_use_label_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(isDisplayed()));
    }
}
