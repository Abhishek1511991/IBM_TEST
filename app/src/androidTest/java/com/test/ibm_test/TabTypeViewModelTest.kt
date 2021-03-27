package com.test.ibm_test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.test.ibm_test.viewmodel.TabTypeViewModel
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/*@RunWith(AndroidJUnit4::class)*/
@RunWith(MockitoJUnitRunner::class)
class TabTypeViewModelTest {

    lateinit var mTypeViewModel: TabTypeViewModel
    private val observer: Observer<String> = mock()


    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        mTypeViewModel= TabTypeViewModel()
        mTypeViewModel._text.observeForever(observer)

    }

    @Test
    fun testViewModel()
    {
        val expectedUser = mTypeViewModel._text.value
        mTypeViewModel.setData("Tab Click")

        val captor = ArgumentCaptor.forClass(String::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals("Tab Click", value)
        }

        //assertEquals(mTypeViewModel._text.getOrAwaitValue(), "Tab Click") // Passes

        //assertEquals(mTypeViewModel._text.getOrAwaitValue(), "Tab") // Fails!



    }

    @After
    fun tearDown() {
    }

    /* Copyright 2019 Google LLC.
   SPDX-License-Identifier: Apache-2.0 */
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}