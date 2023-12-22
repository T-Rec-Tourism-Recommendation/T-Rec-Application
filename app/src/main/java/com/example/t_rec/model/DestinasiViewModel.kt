package com.example.t_rec.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.t_rec.data.Result
import com.example.t_rec.data.response.DestinationResponse
import com.example.t_rec.activity.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinasiViewModel : ViewModel() {

    private val _destinationList = MutableLiveData<Result<List<DestinationResponse>>>()
    val destinationList: LiveData<Result<List<DestinationResponse>>> = _destinationList

    fun searchDestinations(query: String) {
        _destinationList.value = Result.Loading

        val apiService = ApiConfig.getApiService()
        val call: Call<List<DestinationResponse>> = apiService.searchDestinations(query)

        call.enqueue(object : Callback<List<DestinationResponse>> {
            override fun onResponse(
                call: Call<List<DestinationResponse>>,
                response: Response<List<DestinationResponse>>
            ) {
                if (response.isSuccessful) {
                    _destinationList.value = Result.Success(response.body() ?: emptyList())
                } else {
                    _destinationList.value =
                        Result.Error(Exception("Failed to fetch data: ${response.message()}"))
                }
            }

            override fun onFailure(call: Call<List<DestinationResponse>>, t: Throwable) {
                _destinationList.value = Result.Error(t)
            }
        })
    }
}
