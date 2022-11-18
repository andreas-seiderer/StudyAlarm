package com.seiderer.studyalarm.room
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class MomentaryQuestionnaire(
    @PrimaryKey(autoGenerate = true) val uid: Int,

    @ColumnInfo(name = "date") val date: Long,

    @ColumnInfo(name = "q_mood") val q_mood: Int?,                                   // question 1
    @ColumnInfo(name = "q_stressed") val q_stressed: Int?,                           // question 2

    @ColumnInfo(name = "q_physical_activity") val q_physical_activity: Int?,         // question 3 ...
    @ColumnInfo(name = "q_dominant_activity") val q_dominant_activity: String?,

    @ColumnInfo(name = "q_location") val q_location: Int?,

    @ColumnInfo(name = "q_persons") val q_persons: Int?,
)
