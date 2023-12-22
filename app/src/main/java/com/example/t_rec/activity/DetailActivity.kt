package com.example.t_rec.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.t_rec.data.response.DetailResponse
import com.example.t_rec.data.retrofit.ApiService
import com.example.t_rec.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var apiService: ApiService
    private lateinit var detailResponse: DetailResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan ID destinasi dari intent
        val destinationId = intent.getIntExtra(EXTRA_DESTINATION_ID, -1)

        // Memanggil fungsi ApiService untuk mendapatkan detail destinasi
        val call = apiService.getDestinationDetail(destinationId)

        call.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    val detailResponse = response.body()
                    detailResponse?.let {
                        displayDetail(it)
                    }
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                // Handle failure
            }
        })

        binding.btnDetailBack.setOnClickListener {
            finish()
        }

        binding.btnDetailFav.setOnClickListener {
            // Implementasi logika tombol favorit di sini
        }

        binding.ivGoogleMaps.setOnClickListener {
            detailResponse?.let {
                val gmmIntentUri = Uri.parse("geo:${it.google_maps_link}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                if (mapIntent.resolveActivity(packageManager) != null) {
                    startActivity(mapIntent)
                }
            }
        }
    }

    private fun displayDetail(detailResponse: DetailResponse) {
        with(binding) {
            tvDetailName.text = detailResponse.namaTempat
            tvDetailLoc.text = detailResponse.Lokasi
            tvDetailDesc.text = detailResponse.Deskripsi
            tvDetailHtm.text = detailResponse.HTM.toString()

            Glide.with(this@DetailActivity)
                .load(detailResponse.urlImage)
                .into(imgDetail)
        }
    }

    companion object {
        const val EXTRA_DESTINATION_ID = "extra_destination_id"
    }
}
