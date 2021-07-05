package com.ahmadhamwi.medicus_task.domain.entity

public class BiomarkerEntity(
    var id: Int = -1,
    var date: String = "",
    var info: String = "",
    var color: Int = 0x000000,
    var value: String = "",
    var symbol: String = "",
    var insight: String = "",
    var category: String = ""
)