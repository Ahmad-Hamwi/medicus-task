package com.ahmadhamwi.medicus_task.presentation.ui.detail

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.ahmadhamwi.medicus_task.R
import com.ahmadhamwi.medicus_task.databinding.ActivityBiomarkerDetailsBinding
import com.ahmadhamwi.medicus_task.databinding.AlertDialogContentBinding
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI
import com.ahmadhamwi.medicus_task.presentation.ui.base.BindingActivity

class BioMarkerDetailsActivity : BindingActivity<ActivityBiomarkerDetailsBinding>() {

    lateinit var biomarker: BiomarkerUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData();
        initListeners();
    }

    private fun initData() {
        biomarker = intent.extras?.getSerializable(BIOMARKER_EXTRA_KEY) as BiomarkerUI
        binding.biomarker = biomarker
    }

    private fun initListeners() {
        binding.infoCard.setOnClickListener { showAlertDialog(biomarker.info) }
        binding.insightsCard.setOnClickListener { showAlertDialog(biomarker.insight) }
    }

    private fun showAlertDialog(text: String) {
        val dialogBinding = DataBindingUtil.inflate<AlertDialogContentBinding>(
            LayoutInflater.from(this),
            R.layout.alert_dialog_content,
            null,
            false
        )
        dialogBinding.text = text
        AlertDialog.Builder(this)
            .setCancelable(true)
            .setView(dialogBinding.root)
            .create()
            .show()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_biomarker_details
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_start, R.anim.slide_out_end)
    }
}

const val BIOMARKER_EXTRA_KEY = "BIOMARKER_EXTRA_KEY"

//STARTER PATTERN
fun Context.startBiomarkerDetail(biomarker: BiomarkerUI) =
    Intent(this, BioMarkerDetailsActivity::class.java).apply {
        putExtra(BIOMARKER_EXTRA_KEY, biomarker).let { startActivity(this) }
    }