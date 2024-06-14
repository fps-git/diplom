package ru.iteco.fmhandroid.ui.testClasses;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
import ru.iteco.fmhandroid.ui.pageObjects.ControlPanelNews;
import ru.iteco.fmhandroid.ui.pageObjects.CreateNews;
import ru.iteco.fmhandroid.ui.pageObjects.EditNews;
import ru.iteco.fmhandroid.ui.pageObjects.MainScreen;
import ru.iteco.fmhandroid.ui.pageObjects.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class CreateNewsTest {
    TopBar topBar = new TopBar();
    MainScreen mainScreen = new MainScreen();
    LoginScreen loginScreen = new LoginScreen();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    NewsPage newsPage = new NewsPage();

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

    @Description("Test case ID - 3.2 Adding news")
    @Test
    public void successfulNewsCreation() {
        topBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory("Праздник");
        createNews.addTitle("Собрание1");
        createNews.addDate(Utils.currentDate());
        createNews.addTime("00:05");
        createNews.addDescription("Собрание в холле после завтрака");
        createNews.pressSave();
        topBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.searchNewsAndCheckIsDisplayed("Собрание1");
    }


    @Description("Test case ID - 12.2 Adding new with empty fields")
    @Test
    public void unsuccessfulNewAddingWithEmptyFields() {
        topBar.switchToNews();
        newsPage.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.pressSave();
        createNews.verifyNewsCreationFormDisplayed();
    }
}
