package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static ru.iteco.fmhandroid.ui.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Utils;

public class ControlPanelNews {
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    FilterNews filterNews = new FilterNews();
    private final int buttonAddNews = R.id.add_news_image_view;
    private final int buttonEditNews = R.id.edit_news_item_image_view;
    private final int buttonDeleteNews = R.id.delete_news_item_image_view;
    private final ViewInteraction buttonOk = onView(withId(android.R.id.button1));

    public int getButtonAddNews() {
        return buttonAddNews;
    }

    @Step("Нажатие на кнопку 'Добавить' новость")
    public void addNews() {
        Allure.step("Нажатие на кнопку Добавить новость");
        onView(withId(buttonAddNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonAddNews)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 5000));
    }

    @Step("Нажатие на кнопку 'Редактировать' новость")
    public void pressEditPanelNews() {
        Allure.step("Нажатие на кнопку 'Редактировать' новость");
        onView(isRoot()).perform(Utils.waitDisplayed(buttonEditNews, 5000));
        ViewInteraction editButton = onView(withId(buttonEditNews));
        editButton.check(matches(allOf(isDisplayed(), isClickable())));
        editButton.perform(click());
        onView(isRoot()).perform(Utils.waitDisplayed(editNews.getButtonSave(), 5000));
    }

    @Step("Нажатие на кнопку 'Удалить' новость")
    public void deleteNews() {
        Allure.step("Нажатие на кнопку Удалить новость");
        onView(withId(buttonDeleteNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonDeleteNews)).perform(click());
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Поиск новости с заголовком {text} и проверка ее видимости")
    public void searchNewsAndCheckIsDisplayed(String text) {
        Allure.step("Поиск новости с заголовком " + text + " и проверка ее видимости");
        ViewInteraction textTitle = onView(allOf(withText(text), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        textTitle.check(matches(isDisplayed()));
        textTitle.check(matches(withText(endsWith(text))));
    }
    @Step("Проверка отсутствия новости с заголовком {text}")
    public void checkDoesNotExistNews(String text) {
        Allure.step("Проверка отсутствия новости с заголовком {text}");
        onView(withText(text)).check(doesNotExist());
    }
}
