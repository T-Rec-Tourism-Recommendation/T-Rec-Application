//package com.example.t_rec.fragment
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.ViewTreeObserver
//import androidx.annotation.OptIn
//import androidx.core.content.res.ResourcesCompat
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.repeatOnLifecycle
//import com.example.t_rec.DestinasiViewModel
//import com.example.t_rec.R
//import com.example.t_rec.data.utils.ViewUtils
//import com.example.t_rec.databinding.BottomSheetFilterBinding
//import com.google.android.material.badge.BadgeDrawable
//import com.google.android.material.badge.BadgeUtils
//import com.google.android.material.badge.ExperimentalBadgeUtils
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//import com.google.android.material.chip.Chip
//import com.google.android.material.color.MaterialColors
//import kotlinx.coroutines.flow.distinctUntilChanged
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.launch
//
//class FilterBottomSheet : BottomSheetDialogFragment() {
//
//    private var _binding: BottomSheetFilterBinding? = null
//    private val binding get() = _binding!!
//    private val destinasiViewModel: DestinasiViewModel by viewModels()
//    private val viewModel: FilterViewModel by viewModels()
//    private var badgeDrawable: BadgeDrawable? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = BottomSheetFilterBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner
//        return binding.root
//    }
//
//    @OptIn(ExperimentalBadgeUtils::class)
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Prepare the filters badge once the views are laid out
//        binding.btnResetAll.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            @OptIn(ExperimentalBadgeUtils::class)
//            override fun onGlobalLayout() {
//                badgeDrawable =
//                    BadgeDrawable.createFromResource(requireContext(), R.xml.filter_badge).apply {
//                        horizontalOffsetWithText = binding.frameLayout.width / 4
//                        verticalOffsetWithText =
//                            binding.frameLayout.height / 2 + ViewUtils.dpToPx(resources, 24f)
//                                .toInt() / 2
//                        BadgeUtils.attachBadgeDrawable(
//                            this, binding.btnResetAll, binding.frameLayout
//                        )
//                    }
//                updateBadgeDrawable(viewModel.uiState.value.filterCount)
//                binding.btnResetAll.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
//
//        // Fixes bottom sheet not fully expanded inside its parent view
//        requireDialog().setOnShowListener {
//            val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
//            bottomSheetBehavior.isHideable = false
//            val bottomSheetParent = binding.bottomSheetParent
//            BottomSheetBehavior.from(bottomSheetParent.parent as View).peekHeight =
//                bottomSheetParent.height
//            bottomSheetBehavior.peekHeight = bottomSheetParent.height
//            bottomSheetParent.parent.requestLayout()
//        }
//
//        binding.cgCityTypes.setOnCheckedStateChangeListener { _, checkedIds ->
//            if (checkedIds.isEmpty()) viewModel.setCityType(CityType.NONE)
//            else when (checkedIds.first()) {
//                R.id.chipCity1 -> viewModel.setCityType(CityType.YOGYAKARTA_ASC)
//                R.id.chipCity2 -> viewModel.setCityType(CityType.BANDUNG_ASC)
//                R.id.chipCity3 -> viewModel.setCityType(CityType.SEMARANG_ASC)
//                R.id.chipCity4 -> viewModel.setCityType(CityType.SURABAYA_ASC)
//                R.id.chipCity5 -> viewModel.setCityType(CityType.JAKARTA_ASC)
//            }
//        }
//
//        for (index in 0 until binding.cgCategory.childCount) {
//            val chip = binding.cgCategory.getChildAt(index) as Chip
//            val category = getCategoryFromString(chip.text.toString())
//            if (category != null) {
//                chip.isChecked = viewModel.uiState.value.categorys.contains(category)
//                chip.setOnCheckedChangeListener { _, isChecked ->
//                    if (isChecked) viewModel.selectCategory(category)
//                    else viewModel.deselectCategory(category)
//                }
//            } else {
//                Log.e("FilterBottomSheet", "Category not found for text: ${chip.text}")
//            }
//        }
//
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel.uiState.map { it.filterCount }.distinctUntilChanged()
//                        .collect { count ->
//                            updateBadgeDrawable(count)
//                        }
//                }
//
//                launch {
//                    viewModel.uiState.map { it.applyAllFilters }.distinctUntilChanged()
//                        .collect { shouldApplyFilters ->
//                            if (shouldApplyFilters) {
//                                val filterData = FilterData(
//                                    cityType = viewModel.uiState.value.cityType,
//                                    categorys = viewModel.uiState.value.categorys
//                                )
////                                destinasiViewModel.applyFilters(filterData)
////                                 dismiss()
//                            }
//                        }
//                }
//
//                launch {
//                    viewModel.uiState.map { it.clearAllFilters }.distinctUntilChanged()
//                        .collect { shouldResetFilters ->
//                            if (shouldResetFilters) {
////                                 destinasiViewModel.resetFilters()
////                                 dismiss()
//                            }
//                        }
//                }
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    private fun updateBadgeDrawable(number: Int) {
//        when (number > 0) {
//            true -> {
//                badgeDrawable?.number = number
//                badgeDrawable?.backgroundColor =
//                    MaterialColors.getColor(
//                        requireView(),
//                        com.google.android.material.R.attr.colorError
//                    )
//            }
//            false -> {
//                badgeDrawable?.clearNumber()
//                badgeDrawable?.backgroundColor =
//                    ResourcesCompat.getColor(resources, android.R.color.transparent, null)
//            }
//        }
//    }
//
//    private fun getCategoryFromString(text: String): Category? {
//        return Category.values().find { it.category == text }
//    }
//}