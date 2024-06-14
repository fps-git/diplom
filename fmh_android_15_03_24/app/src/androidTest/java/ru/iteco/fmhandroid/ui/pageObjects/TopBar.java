package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Utils;

public class TopBar {
    AboutAppPage aboutAppPage = new AboutAppPage();
    MainScreen mainScreen = new MainScreen();
    OurMission ourMission = new OurMission();
    NewsPage newsPage = new NewsPage();
    int topBarFragmentMain = R.id.container_custom_app_bar_include_on_fragment_main;

    public int getTopBarFragmentMain() {
        return topBarFragmentMain;
    }

    //int pressProfile = R.id.authorization_image_button;

    public int getPressProfile() {
        return R.id.authorization_image_button;
    }

    public ViewInteraction buttonMainMenu = onView(withId(R.id.main_menu_image_button));

    public ViewInteraction mainMenuMain = onView(withText("Главная"));

    public ViewInteraction mainMenuNews = onView(withText("Новости"));

    public ViewInteraction mainMenuAboutApp = onView(withText("О приложении"));

    public ViewInteraction topBarLogo = onView(withId(R.id.trademark_image_view));

    public ViewInteraction buttonOurMission = onView(withId(R.id.our_mission_image_button));

    public ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));

    @Step("Выход из приложения")
    public void logOut() {
        Allure.step("Выход из приложения");
        profileButton.perform(click());
        onView(withText("Выйти")).perform(click());
    }

    @Step("Переход на страницу Новости")
    public void switchToNews() {
        Allure.step("Переход на страницу Новости");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        mainMenuNews.check(matches(isDisplayed()));
        mainMenuNews.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(newsPage.getContainerNews(), 5000));
    }

    @Step("Переход на страницу О приложении")
    public void AboutApp() {
        Allure.step("Переход на страницу О приложении");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        mainMenuAboutApp.check(matches(isDisplayed()));
        mainMenuAboutApp.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(aboutAppPage.getButtonBack(), 5000));
    }

    @Step("Переход на страницу Главная")
    public void mainScreen() {
        Allure.step("Переход на страницу Главная");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        mainMenuMain.check(matches(isDisplayed()));
        mainMenuMain.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(mainScreen.getContainerNews(), 5000));
    }

    @Step("Переход на страницу 'Наша миссия'")
    public void pageOurMission() {
        Allure.step("Переход на страницу 'Наша миссия'");
        buttonOurMission.check(matches(isDisplayed()));
        buttonOurMission.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(ourMission.getTitle(), 5000));
    }
}