package net.simplifiedcoding.spendtracker.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import net.simplifiedcoding.spendtracker.data.Spend
import net.simplifiedcoding.spendtracker.data.SpendsDatabase
import net.simplifiedcoding.spendtracker.data.SpendsTrackerDataSource
import net.simplifiedcoding.spendtracker.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class SpendViewModelTest {

    private lateinit var viewModel: SpendViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, SpendsDatabase::class.java)
            .allowMainThreadQueries().build()
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        viewModel = SpendViewModel(dataSource)
    }

    @Test
    fun testSpendViewModel(){
        viewModel.addSpend(170, "Bought some flowers")
        viewModel.getLast20Spends()
        val result = viewModel.last20SpendsLiveData.getOrAwaitValue().find {
            it.amount == 170 && it.description == "Bought some flowers"
        }
        assertThat(result != null).isTrue()
    }

}