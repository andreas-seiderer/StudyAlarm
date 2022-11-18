package com.seiderer.studyalarm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InitialQuestionnaireDao {

    @Query("SELECT * FROM initialQuestionnaire")
    fun getAll(): List<InitialQuestionnaire>

    @Insert
    fun insertAll(vararg questionnaire: InitialQuestionnaire)

    @Query("SELECT * FROM initialQuestionnaire ORDER BY date DESC LIMIT 1")
    fun mostRecentEntry(): InitialQuestionnaire?
}