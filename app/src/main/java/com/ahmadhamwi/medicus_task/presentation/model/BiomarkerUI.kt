package com.ahmadhamwi.medicus_task.presentation.model

import androidx.databinding.ObservableBoolean
import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import java.io.Serializable

class BiomarkerUI(
    val id: Int,
    val date: String,
    val info: String,
    val color: Int,
    val value: String,
    val symbol: String,
    val insight: String,
    val category: String
) : Serializable {

    /**
     * Used to display the loading state of a biomarker item.
     * In our case, a biomarker can be fetched again , so this field is used.
     */
    @Transient
    val isReloading: ObservableBoolean = ObservableBoolean(false)

    fun getReloadingValue(): Boolean {
        return isReloading.get()
    }

    fun setReloading(value: Boolean) {
        isReloading.set(value)
    }

    companion object {
        fun fromEntity(entity: BiomarkerEntity): BiomarkerUI {
            return BiomarkerUI(
                entity.id,
                entity.date,
                entity.info,
                entity.color,
                entity.value,
                entity.symbol,
                entity.insight,
                entity.category
            )
        }
    }

    fun isValid(): Boolean {
        return !symbol.isEmpty()
    }
}