package com.seiderer.studyalarm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DailyQuestionnaireDao {
    @Query("SELECT * FROM dailyQuestionnaire")
    fun getAll(): List<DailyQuestionnaire>

    @Insert
    fun insertAll(vararg questionnaire: DailyQuestionnaire)

    @Query("SELECT * FROM dailyQuestionnaire ORDER BY date DESC LIMIT 1")
    fun mostRecentEntry(): DailyQuestionnaire?
}