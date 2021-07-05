package com.ahmadhamwi.medicus_task.infrastructure.api.model

import android.graphics.Color
import com.ahmadhamwi.medicus_task.domain.entity.BiomarkerEntity
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

class BiomarkerApiModel(
    @Nullable @SerializedName("id") val id: Int?,
    @Nullable @SerializedName("date") val date: String?,
    @Nullable @SerializedName("info") val info: String?,
    @Nullable @SerializedName("color") val color: String?,
    @Nullable @SerializedName("value") val value: String?,
    @Nullable @SerializedName("symbol") val symbol: String?,
    @Nullable @SerializedName("insight") val insight: String?,
    @Nullable @SerializedName("category") val category: String?
) {

    fun toEntity(): BiomarkerEntity {
        return BiomarkerEntity(
            id = id ?: -1,
            date = date ?: "",
            info = info ?: "",
            color = if (color == null) 0 else Color.parseColor(color),
            value = value ?: "",
            symbol = symbol ?: "",
            insight = insight ?: "",
            category = category ?: ""
        )
    }
}