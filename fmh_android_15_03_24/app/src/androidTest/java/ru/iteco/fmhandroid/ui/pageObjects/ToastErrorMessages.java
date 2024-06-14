package ru.iteco.fmhandroid.ui.pageObjects;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.ToastMatcher;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ToastErrorMessages {

    ToastMatcher toastMatcher = new ToastMatcher();
    private static final String invalidLoginMessage = "Что-то пошло не так. Попробуйте позднее.";
    private static final String messageWhenEmptyLoginField = "Логин и пароль не могут быть пустыми";

    @Step("Check of error message displaying when invalid login")
    public void checkToastErrorWhenInvalidUserData() {
        onView(withText(invalidLoginMessage)).inRoot(toastMatcher)
                .check(matches(isDisplayed()));
    }
    @Step("Check of error message displaying when empty login fields")
    public void checkToastErrorWhenEmptyLoginFields() {

       onView(withText(messageWhenEmptyLoginField)).inRoot(toastMatcher)
              .check(matches(isDisplayed()));
    }

    @Step("Check text of error message")
    public void checkToastErrorText(String expectedText) {

        onView(withText(expectedText)).inRoot(toastMatcher)
                .check(matches(isDisplayed()));
    }
}
