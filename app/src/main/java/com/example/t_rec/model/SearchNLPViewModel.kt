package com.example.t_rec.model

////import androidx.lifecycle.LiveData
////import androidx.lifecycle.MutableLiveData
////import androidx.lifecycle.ViewModel
////
////class SearchNLPViewModel : ViewModels() {
////    private val _userListDestination = MutableLiveData<List<DestinationResponseItem>?>()
////    val userListDestinatin: MutableLiveData<List<DestinationResponseItem>?> get() = _userListDestination
////
////    fun setUserListDestination(responseBody: List<DestinationResponseItem>?) {
////        _userListDestination.value = responseBody
////    }
////
////    private val _loading = MutableLiveData<Boolean>()
////    val loading: LiveData<Boolean> get() = _loading
////
////    fun setLoading(isLoading: Boolean) {
////        _loading.value = isLoading
////    }
////    init {
////        setLoading(true)
////    }
////}