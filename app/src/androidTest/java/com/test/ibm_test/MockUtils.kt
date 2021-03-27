package com.test.ibm_test

import org.mockito.Mockito

/**
 * Helper function to mock classes with types (generics)
 */
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)