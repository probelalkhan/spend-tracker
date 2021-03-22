package net.simplifiedcoding.spendtracker.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import net.simplifiedcoding.spendtracker.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddSpendFragmentTest {

    private lateinit var scenario: FragmentScenario<AddSpendFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_SpendTracker)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testAddingSpend() {
        val amount = 100
        val desc = "Bought Eggs"
        //Espresso Matcher and Action
        onView(withId(R.id.edit_text_amount)).perform(typeText(amount.toString()))
        onView(withId(R.id.edit_text_description)).perform(typeText(desc))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.button_add)).perform(click())

        //Assertion
        onView(withId(R.id.text_view_success_message)).check(matches(withText("Spend Added")))
    }
}