package com.example.t_rec.model

data class DestinationFilterModel(
    val searchQuery: String? = null,
    val cityType: CityType = CityType.NONE,
    val category: Set<Category> = setOf(),
)

enum class CityType {
    NONE,
    YOGYAKARTA_ASC,
    BANDUNG_ASC,
    SEMARANG_ASC,
    SURABAYA_ASC,
    JAKARTA_ASC,
}

enum class Category(val category: String) {
    BUDAYA("Budaya"),
    TAMAN_HIBURAN("Taman Hiburan"),
    CAGAR_ALAM("Cagar Alam"),
    BAHARI("Bahari"),
    TEMPAT_IBADAH("Tempat Ibadah"),
    PUSAT_PERBELANJAAN("Pusat Perbelanjaan")
}