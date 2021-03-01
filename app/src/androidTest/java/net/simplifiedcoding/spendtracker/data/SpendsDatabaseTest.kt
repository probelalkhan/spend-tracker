package net.simplifiedcoding.spendtracker.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class SpendsDatabaseTest : TestCase() {

    private lateinit var spendsDao: SpendDao
    private lateinit var db: SpendsDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SpendsDatabase::class.java
        ).build()
        spendsDao = db.getSpendDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadSpend() = runBlocking {
        val spend = Spend(Date(), 100, "for Bread")
        spendsDao.addSpend(spend)
        val spends = spendsDao.getLast20Spends()
        assertThat(spends.contains(spend)).isTrue()
    }
}