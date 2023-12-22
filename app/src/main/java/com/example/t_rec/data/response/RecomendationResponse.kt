package com.example.t_rec.data.response

data class RecomendationResponse(
    val rekomendasi: String,
    val destinasi: List<DetailResponse>
)
