package com.ahmadhamwi.medicus_task.presentation.ui.biomarkers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.medicus_task.R
import com.ahmadhamwi.medicus_task.databinding.ItemBiomarkerBinding
import com.ahmadhamwi.medicus_task.databinding.ItemInvalidBiomarkerBinding
import com.ahmadhamwi.medicus_task.presentation.model.BiomarkerUI

class BiomarkersAdapter(
    private val context: Context,
    private var biomarkers: List<BiomarkerUI>,
    private val onValidClicked: OnBiomarkerItemClicked,
    private val onInvalidClicked: OnBiomarkerInvalidItemClicked
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        /**
         * The type "ok" is rendered when a list object has the appropriate data to show.
         */
        const val BIOMARKER_OK = 1

        /**
         * The type "invalid" is rendered as an item because some of the items have no data in them.
         * So it might be a good idea to demonstrate multiple item types and how to respond to their
         * click events.
         */
        const val BIOMARKER_INVALID = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (biomarkers[position].isValid()) {
            BIOMARKER_OK
        } else {
            BIOMARKER_INVALID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == BIOMARKER_OK) {
            return BiomarkerViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.item_biomarker,
                    parent,
                    false
                )
            )
        } else if (viewType == BIOMARKER_INVALID) {
            return BiomarkerInvalidViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.item_invalid_biomarker,
                    parent,
                    false
                )
            )
        }
        throw Exception("Unhandled viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BiomarkerViewHolder) {
            holder.bind(biomarkers[position], onValidClicked)
        } else if (holder is BiomarkerInvalidViewHolder) {
            holder.bind(biomarkers[position], onInvalidClicked)
        }
    }

    override fun getItemCount(): Int {
        return biomarkers.size
    }

    /**
     * Replacing lists is bad when we're talking about small changes on the list...
     * However, it should get the job done if the list size is not too big.
     * We should probably be using DiffUtil to know exactly what changed in the list
     */
    fun replaceList(biomarkers: List<BiomarkerUI>) {
        this.biomarkers = biomarkers
        notifyDataSetChanged()
    }

    fun findAndReplace(reloadedBiomarker: BiomarkerUI) {
        for (biomarker: BiomarkerUI in biomarkers) {
            if (reloadedBiomarker.id == biomarker.id) {
                val mutableList = biomarkers.toMutableList()
                mutableList[biomarkers.indexOf(biomarker)] = reloadedBiomarker
                biomarkers = mutableList
                notifyItemChanged(biomarkers.indexOf(reloadedBiomarker))
                return
            }
        }
    }

    interface OnBiomarkerItemClicked {
        fun onBiomarkerItemClicked(biomarker: BiomarkerUI)
    }

    interface OnBiomarkerInvalidItemClicked {
        fun onBiomarkerInvalidItemClicked(biomarker: BiomarkerUI)
    }

    class BiomarkerViewHolder(private val binding: ItemBiomarkerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(biomarkerUI: BiomarkerUI, callback: OnBiomarkerItemClicked) {
            binding.biomarker = biomarkerUI
            binding.card.setOnClickListener { callback.onBiomarkerItemClicked(biomarkerUI) }
        }
    }

    class BiomarkerInvalidViewHolder(private val binding: ItemInvalidBiomarkerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(biomarker: BiomarkerUI, callback: OnBiomarkerInvalidItemClicked) {
            binding.biomarker = biomarker
            binding.root.setOnClickListener { callback.onBiomarkerInvalidItemClicked(biomarker) }
        }
    }
}


