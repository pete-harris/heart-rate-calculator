package uk.me.peteharris.heartratecalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.SeekBar;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by pharris on 30/08/16.
 */

@RunWith(AndroidJUnit4.class)
public class CalculationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<MainActivity>(MainActivity.class){
        @Override
        protected void beforeActivityLaunched() {
            clearSharedPrefs(InstrumentationRegistry.getTargetContext());
            super.beforeActivityLaunched();
        }
    };

    private void clearSharedPrefs(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(MainActivity.class.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    @Before
    public void initRates(){
        onView(withId(R.id.maxheartrate))
                .perform(typeText("200"), closeSoftKeyboard());
        onView(withId(R.id.minheartrate))
                .perform(typeText("50"), closeSoftKeyboard());
    }

    @Test
    public void testSlider(){
        onView(withId(R.id.seekBar))
                .perform(setProgress(40));
        onView(withId(R.id.percentageLabel))
                .check(matches(withText("Percentage: 40 %")));
        onView(withId(R.id.heartRateLabel))
                .check(matches(withText("110")));


        onView(withId(R.id.seekBar))
                .perform(setProgress(80));
        onView(withId(R.id.percentageLabel))
                .check(matches(withText("Percentage: 80 %")));
        onView(withId(R.id.heartRateLabel))
                .check(matches(withText("170")));


        onView(withId(R.id.maxheartrate))
                .perform(clearText(), typeText("170"), closeSoftKeyboard());
        onView(withId(R.id.percentageLabel))
                .check(matches(withText("Percentage: 80 %")));
        onView(withId(R.id.heartRateLabel))
                .check(matches(withText("146")));
    }
    }


    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                ((SeekBar) view).setProgress(progress);
            }

            @Override
            public String getDescription() {
                return "Set a progress";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }
        };
    }
}
