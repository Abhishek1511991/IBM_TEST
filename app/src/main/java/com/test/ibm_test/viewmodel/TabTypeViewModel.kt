package com.test.ibm_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TabTypeViewModel : ViewModel() {

    public val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun setData(name: String?) {
        _text.value=name
    }
}