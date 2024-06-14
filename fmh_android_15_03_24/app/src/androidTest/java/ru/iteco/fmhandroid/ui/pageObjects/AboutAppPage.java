package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AboutAppPage {

    public ViewInteraction privacyPolicyText = onView(withId(R.id.about_privacy_policy_label_text_view));
    public ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction termsText = onView(withId(R.id.about_terms_of_use_label_text_view));
    public ViewInteraction termsLink = onView(withId(R.id.about_terms_of_use_value_text_view));

    int buttonBack = R.id.about_back_image_button;

    public int getButtonBack() {
        return buttonBack;
    }

    @Step("Проверка текста политики конфиденциальности")
    public void policyTextCheck(String text) {
        Allure.step("Проверка текста политики конфиденциальности");
        privacyPolicyText.check(matches(withText(text)));
    }

    @Step("Проверка ссылки политики конфиденциальности")
    public void policyLinkCheck(String url) {
        Allure.step("Проверка ссылки политики конфиденциальности");
        privacyPolicyLink.check(matches(withText(url)));
    }

    @Step("Проверка текста пользовательского соглашения")
    public void termsTextCheck(String text) {
        Allure.step("Проверка текста пользовательского соглашения");
        termsText.check(matches(withText(text)));
    }
    @Step("Проверка ссылки пользовательского соглашения")
    public void termsLinkCheck(String url) {
        Allure.step("Проверка ссылки пользовательского соглашения");
        termsLink.check(matches(withText(url)));
    }

    @Step("Нажатие на кнопку Назад")
    public void back() {
        Allure.step("Нажатие на кнопку Назад");
        onView(withId(buttonBack)).perform(click());
    }
}


