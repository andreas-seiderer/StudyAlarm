package com.seiderer.studyalarm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MomentaryQuestionnaireDao {
    @Query("SELECT * FROM momentaryQuestionnaire")
    fun getAll(): List<MomentaryQuestionnaire>

    @Insert
    fun insertAll(vararg questionnaire: MomentaryQuestionnaire)
}