package com.effectivemobilett.presentation.main.searchfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.effectivemobilett.presentation.main.MainViewModel
import com.example.effectivemobilett.R
import com.example.effectivemobilett.databinding.SearchCategoryFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCategoryFilterBottomSheet : BottomSheetDialogFragment() {

    private var _binding: SearchCategoryFilterBottomSheetBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchCategoryFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mobileDevLabel = binding?.filterBottomSheetMobileDevLabel
        val mobileDevContainer = binding?.filterBottomSheetPatternsContainerMobileDevLinear

        val difficultLabel = binding?.filterBottomSheetDifficultLabel
        val difficultContainer = binding?.filterBottomSheetPatternsContainerDifficultLinear

        val costDevLabel = binding?.filterBottomSheetCostLabel
        val costContainer = binding?.filterBottomSheetPatternsContainerCostLinear

        getSelectedQuery()
        setupExpandableSection(mobileDevLabel, mobileDevContainer)
        setupExpandableSection(difficultLabel, difficultContainer)
        setupExpandableSection(costDevLabel, costContainer)

        binding?.filterBottomSheetApplyTextButton?.setOnClickListener {
            viewModel.searchByName(getSelectedQuery())
            this.dismiss()
        }
    }

    private fun setupExpandableSection(label: TextView?, container: LinearLayout?) {
        label?.setOnClickListener {
            if (container?.visibility == View.VISIBLE) {
                container.visibility = View.GONE
                label.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0)
            } else {
                container?.visibility = View.VISIBLE
                label.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
            }
        }
    }

    private fun getSelectedQuery(): String {
        val selectedQueries = mutableListOf<String>()

        if (binding?.filterSearchRequestKotlinCheckbox!!.isChecked) selectedQueries.add(getString(R.string.filter_search_pattern_kotlin))
        if (binding?.filterSearchRequestAndroidCheckbox!!.isChecked) selectedQueries.add(getString(R.string.filter_search_pattern_android))
        if (binding?.filterSearchRequestUIUXCheckbox!!.isChecked) selectedQueries.add(getString(R.string.filter_search_pattern_UIUX))

        if (binding?.filterSearchRequestBeginnerCheckbox!!.isChecked) selectedQueries.add(
            getString(
                R.string.filter_search_pattern_beginner
            )
        )
        if (binding?.filterSearchRequestIntermediateCheckbox!!.isChecked) selectedQueries.add(
            getString(R.string.filter_search_pattern_intermediate)
        )
        if (binding?.filterSearchRequestAdvancedCheckbox!!.isChecked) selectedQueries.add(
            getString(
                R.string.filter_search_pattern_advanced
            )
        )

        if (binding?.filterSearchRequestFreeCheckbox!!.isChecked) selectedQueries.add(getString(R.string.filter_search_pattern_free))
        if (binding?.filterSearchRequestPaidCheckbox!!.isChecked) selectedQueries.add(getString(R.string.filter_search_pattern_paid))

        return selectedQueries.joinToString(", ")
    }
}
