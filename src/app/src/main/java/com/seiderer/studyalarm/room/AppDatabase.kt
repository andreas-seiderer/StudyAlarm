package com.seiderer.studyalarm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InitialQuestionnaire::class, DailyQuestionnaire::class, MomentaryQuestionnaire::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun initialQuestionnaireDao(): InitialQuestionnaireDao
    abstract fun dailyQuestionnaireDao(): DailyQuestionnaireDao
    abstract fun momentaryQuestionnaireDao(): MomentaryQuestionnaireDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "study_alarm_database")
                .allowMainThreadQueries()
                .build()
        }
    }
}