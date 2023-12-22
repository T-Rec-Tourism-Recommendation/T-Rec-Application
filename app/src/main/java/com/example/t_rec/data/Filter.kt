//package com.example.t_rec.data
//
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//
//
//const val KEY_FILTER_DATA = "filter_data"
//
//data class FilterData(
//    val cityType: CityType = CityType.NONE,
//    val categorys: Set<Category> = setOf(),
//) : java.io.Serializable
//
//
//class FilterViewModel (savedStateHandle: SavedStateHandle) : ViewModel() {
//
//    data class UiState(
//        val cityType: CityType = CityType.NONE,
//        val categorys: Set<Category> = setOf(),
//        val clearAllFilters: Boolean = false,
//        val applyAllFilters: Boolean = false
//    ) {
//        val filterCount: Int
//            get() {
//                val cityCount = if (cityType == CityType.NONE) 0 else 1
//                val filterCount = categorys.size
//                return cityCount + filterCount
//            }
//
//
//
//        val chipCity1IsChecked = cityType == CityType.YOGYAKARTA_ASC
//        val chipCity2IsChecked = cityType == CityType.BANDUNG_ASC
//        val chipCity3IsChecked = cityType == CityType.SEMARANG_ASC
//        val chipCity4IsChecked = cityType == CityType.SURABAYA_ASC
//        val chipCity5IsChecked = cityType == CityType.JAKARTA_ASC
//
//    }
//
//    private val _uiState = MutableStateFlow(UiState())
//    val uiState = _uiState.asStateFlow()
//
//
//    init {
//
//        savedStateHandle.get<FilterData>(KEY_FILTER_DATA)?.let { filterData ->
//            _uiState.update {
//                it.copy(cityType = filterData.cityType, categorys = filterData.categorys)
//            }
//        }
//
//    }
//
//    fun setCityType(cityType: CityType) {
//        _uiState.update {
//            it.copy(cityType = cityType)
//        }
//    }
//
//    fun selectCategory (category: Category) {
//        _uiState.update {
//            val categorys = it.categorys.toMutableSet().apply {
//                add(category)
//            }
//            it.copy(categorys = categorys)
//        }
//    }
//
//    fun deselectCategory(category: Category) {
//        _uiState.update {
//            val categorys = it.categorys.toMutableSet().apply {
//                remove(category)
//            }
//            it.copy(categorys = categorys)
//        }
//    }
//
//    fun resetFilters() {
//        _uiState.update {
//            it.copy(clearAllFilters = true, categorys = setOf(), cityType = CityType.NONE)
//        }
//    }
//
//    fun applyFilters() {
//        _uiState.update {
//            it.copy(applyAllFilters = true)
//        }
//    }
//
//
//
//
//}