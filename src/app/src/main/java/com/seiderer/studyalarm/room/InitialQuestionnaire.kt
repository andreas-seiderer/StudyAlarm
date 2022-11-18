package com.seiderer.studyalarm.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity
@Serializable
data class InitialQuestionnaire(
    @PrimaryKey(autoGenerate = true) val uid: Int,

    @ColumnInfo(name = "date") val date: Long,

    @ColumnInfo(name = "q_age") val q_age: Int?,                        // question 1
    @ColumnInfo(name = "q_gender") val q_gender: Int?,                  // question 2
    @ColumnInfo(name = "q_dominant_hand") val q_dominant_hand: Int?,    // question 3 ...
    @ColumnInfo(name = "q_wrist_wearable") val q_wrist_wearable: Int?,

    @ColumnInfo(name = "q_height_cm") val q_height_cm: Int?,
    @ColumnInfo(name = "q_weight_kg") val q_weight_kg: Int?,

    @ColumnInfo(name = "q_profession") val q_profession: String?,

    @ColumnInfo(name = "q_mindfulness_techniques") val q_mindfulness_techniques: Int?,
    @ColumnInfo(name = "q_help_mindfulness_interventions") val help_mindfulness_interventions: Int?,

    @ColumnInfo(name = "q_big5_01") val q_big5_01: Int?,
    @ColumnInfo(name = "q_big5_02") val q_big5_02: Int?,
    @ColumnInfo(name = "q_big5_03") val q_big5_03: Int?,
    @ColumnInfo(name = "q_big5_04") val q_big5_04: Int?,
    @ColumnInfo(name = "q_big5_05") val q_big5_05: Int?,
    @ColumnInfo(name = "q_big5_06") val q_big5_06: Int?,
    @ColumnInfo(name = "q_big5_07") val q_big5_07: Int?,
    @ColumnInfo(name = "q_big5_08") val q_big5_08: Int?,
    @ColumnInfo(name = "q_big5_09") val q_big5_09: Int?,
    @ColumnInfo(name = "q_big5_10") val q_big5_10: Int?,
    )
