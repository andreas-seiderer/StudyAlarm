package com.seiderer.studyalarm.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class DailyQuestionnaire(
    @PrimaryKey(autoGenerate = true) val uid: Int,

    @ColumnInfo(name = "date") val date: Long,

    @ColumnInfo(name = "q_sleep_quality") val q_sleep_quality: Int?,  // question 1

    @ColumnInfo(name = "q_BDSST01") val q_BDSST01: Int?,  // question 2
    @ColumnInfo(name = "q_BDSST02") val q_BDSST02: Int?,  // question 3 ...
    @ColumnInfo(name = "q_BDSST03") val q_BDSST03: Int?,
    @ColumnInfo(name = "q_BDSST04") val q_BDSST04: Int?,
    @ColumnInfo(name = "q_BDSST05") val q_BDSST05: Int?,
    @ColumnInfo(name = "q_BDSST06") val q_BDSST06: Int?,
    @ColumnInfo(name = "q_BDSST07") val q_BDSST07: Int?,
    @ColumnInfo(name = "q_BDSST08") val q_BDSST08: Int?,
    @ColumnInfo(name = "q_BDSST09") val q_BDSST09: Int?,
    @ColumnInfo(name = "q_BDSST10") val q_BDSST10: Int?,
    )
