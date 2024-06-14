package ru.iteco.fmhandroid.ui.pageObjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static kotlinx.coroutines.flow.FlowKt.first;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class OurMission {

    public ViewInteraction title = onView(allOf(withId(R.id.our_mission_title_text_view)));


    public ViewInteraction item = onView(allOf(withId(R.id.our_mission_item_title_text_view)));

    public ViewInteraction openCardButton = onView(withId(R.id.our_mission_item_open_card_image_button));

    public ViewInteraction itemDescription = onView(withId(R.id.our_mission_item_description_text_view));

    public int getTitle() {
        return R.id.our_mission_title_text_view;
    }
}
