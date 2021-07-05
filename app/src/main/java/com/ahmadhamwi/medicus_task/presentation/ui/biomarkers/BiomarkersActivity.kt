package com.ahmadhamwi.medicus_task.presentation.ui.biomarkers

import android.os.Bundle
import androidx.activity.viewModels
import com.ahmadhamwi.medicus_task.R
import com.ahmadhamwi.medicus_task.databinding.ActivityBiomarkersBinding
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI
import com.ahmadhamwi.medicus_task.presentation.ui.base.BindingActivity
import com.ahmadhamwi.medicus_task.presentation.ui.detail.BioMarkerDetailsActivity
import com.ahmadhamwi.medicus_task.presentation.ui.detail.startBiomarkerDetail
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BiomarkersActivity : BindingActivity<ActivityBiomarkersBinding>(),
    BiomarkersAdapter.OnBiomarkerItemClicked, BiomarkersAdapter.OnBiomarkerInvalidItemClicked {

    private val viewModel: BiomarkersViewModel by viewModels()

    private val biomarkersAdapter = BiomarkersAdapter(
        this,
        mutableListOf(),
        this,
        this
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_biomarkers
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initList()
        observeViewModel()
    }

    fun initList() {
        binding.list.adapter = biomarkersAdapter
    }

    private fun observeViewModel() {
        viewModel.getBiomarkersLiveData().observe(this, { biomarkers ->
            biomarkersAdapter.replaceList(biomarkers)
        })
        viewModel.getBiomarkerReloadedLiveData().observe(this, { reloadedBiomarker ->
            if (reloadedBiomarker != null) {
                biomarkersAdapter.findAndReplace(reloadedBiomarker)
            }
        })
    }

    override fun onBiomarkerItemClicked(biomarker: BiomarkerUI) {
        startBiomarkerDetail(biomarker)
        overridePendingTransition(R.anim.slide_in_end, R.anim.slide_out_start)
    }

    override fun onBiomarkerInvalidItemClicked(biomarker: BiomarkerUI) {
        viewModel.getBiomarker(biomarker)
    }
}