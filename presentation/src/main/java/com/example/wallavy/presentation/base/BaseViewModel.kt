package com.example.wallavy.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallavy.domain.model.ResponseData
import com.example.wallavy.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class BaseViewModel: ViewModel() {

    val toastLiveData = MutableLiveData<String>()

    private val _errorLiveData = MutableLiveData<Pair<String, String>>()
    val errorLiveData: LiveData<Pair<String, String>> get() = _errorLiveData

    private val _loadLiveData = MutableLiveData<Boolean>()
    val loadLiveData: LiveData<Boolean> get() = _loadLiveData

//    val appBarData = AppBarData(title = "")

    protected fun <T> Flow<Result<ResponseData<T>>>.callResponse(block: (ResponseData<T>) -> Unit) {
        onEach {
            when(it) {
                is Result.Loading -> {
                    _loadLiveData.value = it.start
                }
                is Result.Success -> {
                    if(it.data.code == "100")
                        block.invoke(it.data)
                    else
                        _errorLiveData.value = it.data.code to it.data.msg
                }
                is Result.Error -> {
                    _errorLiveData.value = it.code to it.msg
                }
            }
        }.launchIn(viewModelScope)
    }
}