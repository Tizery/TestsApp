package com.example.testsapp

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tests.TEST_NUMBER_OF_RESULTS_MINUS_1
import com.example.tests.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.example.testsapp.view.details.DetailsFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<DetailsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testBundle() {
        val fragmentArgs = bundleOf("TOTAL_COUNT_EXTRA" to 10)
        val scenario = launchFragmentInContainer<DetailsFragment>(fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = ViewAssertions.matches(ViewMatchers.withText("Number of results: 10"))
        Espresso.onView(withId(R.id.totalCountTextView)).check(assertion)
    }

    @Test
    fun fragment_testSetCountMethod() {
        scenario.onFragment { fragment ->
            fragment.setCount(10)
        }
        val assertion = ViewAssertions.matches(ViewMatchers.withText("Number of results: 10"))
        Espresso.onView(withId(R.id.totalCountTextView)).check(assertion)
    }

    @Test
    fun fragment_testIncrementButton() {
        Espresso.onView(withId(R.id.incrementButton)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.totalCountTextView))
            .check(ViewAssertions.matches(withText(TEST_NUMBER_OF_RESULTS_PLUS_1)))
    }

    @Test
    fun fragment_testDecrementButton() {
        Espresso.onView(withId(R.id.decrementButton)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.totalCountTextView))
            .check(ViewAssertions.matches(withText(TEST_NUMBER_OF_RESULTS_MINUS_1)))
    }
}