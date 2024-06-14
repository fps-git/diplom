package ru.iteco.fmhandroid.ui.testClasses;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.Utils;
import ru.iteco.fmhandroid.ui.pageObjects.TopBar;
import ru.iteco.fmhandroid.ui.pageObjects.LoginScreen;
import ru.iteco.fmhandroid.ui.pageObjects.ControlPanelNews;
import ru.iteco.fmhandroid.ui.pageObjects.FilterNews;
import ru.iteco.fmhandroid.ui.pageObjects.MainScreen;
import ru.iteco.fmhandroid.ui.pageObjects.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsTest {
    TopBar topBar = new TopBar();
    FilterNews filterNews = new FilterNews();
    NewsPage newsPage = new NewsPage();
    MainScreen mainScreen = new MainScreen();
    LoginScreen loginScreen = new LoginScreen();

    ControlPanelNews controlPanelNews = new ControlPanelNews();

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

    @Description("Test case ID - 29.4 Category filtering test")
    @Test
    public void inputCategoriesNewsInCyrillic() {
        topBar.switchToNews();
        newsPage.openFormFilterNews();
        List<String> categories = Arrays.asList(
                "День рождения",
                "Объявление",
                "Зарпалата",
                "Профсоюз",
                "Праздник",
                "Массаж",
                "Благодарность",
                "Нужна помощь"
        );
        for (String category : categories) {
            filterNews.addCategoryFilter(category);
        }
    }
}


