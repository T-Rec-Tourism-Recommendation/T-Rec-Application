package com.example.t_rec.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailResponse(
    val ID: Int,
    val urlImage: String,
    val namaTempat: String,
    val Lokasi: String,
    val HTM: Int,
    val Deskripsi: String,
    val google_maps_link: String


): Parcelable
