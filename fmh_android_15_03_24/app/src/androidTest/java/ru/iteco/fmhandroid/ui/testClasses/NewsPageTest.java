package ru.iteco.fmhandroid.ui.testClasses;


import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Utils;
import ru.iteco.fmhandroid.ui.pageObjects.TopBar;
import ru.iteco.fmhandroid.ui.pageObjects.LoginScreen;
import ru.iteco.fmhandroid.ui.pageObjects.MainScreen;
import ru.iteco.fmhandroid.ui.pageObjects.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    LoginScreen loginScreen = new LoginScreen();
    NewsPage newsPage = new NewsPage();
    TopBar topBar = new TopBar();
    MainScreen mainScreen = new MainScreen();

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

    @Description("Test case ID - 28.4 Back sorting test")
    @Test
    public void sortingNews() {
        topBar.switchToNews();
        newsPage.buttonSortingNews();
    }
}