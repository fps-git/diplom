package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class MainScreen {
    private final int containerNews = R.id.container_list_news_include_on_fragment_main;
    private final int buttonAllNews = R.id.all_news_text_view;
    public ViewInteraction textViewNewsOnPageMain = onView(withText("Новости"));
    public ViewInteraction allNews = onView(withText("Все новости"));
    public ViewInteraction lastNews = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction expandButton = onView(withId(R.id.expand_material_button));

    public int getContainerNews() {
        return containerNews;
    }

    @Step("News visibility check")
    public void checkNews() {
        Allure.step("News visibility check");
        onView(withId(containerNews)).check(matches(isDisplayed()));
        textViewNewsOnPageMain.check(matches(withText("Новости")));
    }


    @Step("Screen check: true - main screen, false - login screen")
    public Boolean isDisplayedButtonProfile() {
        try {
            onView(withId(containerNews)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException noMatchingViewException) {
            return false;
        }
    }
}