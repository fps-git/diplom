package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.Utils;

public class LoginScreen {
    MainScreen mainScreen = new MainScreen();
    TopBar topBar = new TopBar();

    @Step("Ввод логина")
    public void inputInFieldLogin(String login) {
        Allure.step("Ввод логина");
        ViewInteraction inputInFieldLogin = onView(withHint("Логин"));
        inputInFieldLogin.perform(replaceText(login));
        closeSoftKeyboard();
    }

    @Step("Ввод пароля")
    public void inputInFieldPassword(String password) {
        Allure.step("Ввод пароля");
        ViewInteraction inputInFieldPassword = onView(withHint("Пароль"));
        inputInFieldPassword.perform(replaceText(password), closeSoftKeyboard());
        pressImeActionButton();
        pressBack();
    }

    @Step("Нажатие на кнопку Войти")
    public void pressButton() {
        Allure.step("Нажатие на кнопку Войти");
        ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));
        buttonSingIn.check(matches(isDisplayed()));
        buttonSingIn.perform(click());
    }

    @Step("Проверка видимости элемента с текстом Авторизация")
    public void visibilityElement() {
        Allure.step("Проверка видимости элемента с текстом Авторизация");
        ViewInteraction textViewAuth = onView(withText("Авторизация"));
        textViewAuth.check(matches(isDisplayed()));
        textViewAuth.check(matches(withText(endsWith("Авторизация"))));
    }

    @Step("Успешная авторизация пользователя")
    public void successfulAuthorization() {
        Allure.step("Успешная авторизация пользователя");
        inputInFieldLogin("login2");
        inputInFieldPassword("password2");
        pressButton();
        onView(isRoot()).perform(Utils.waitDisplayed(topBar.getPressProfile(), 5000));
        mainScreen.checkNews();
    }
}
