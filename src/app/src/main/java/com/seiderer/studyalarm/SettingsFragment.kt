package com.seiderer.studyalarm

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.softwork.serialization.csv.CSVFormat
import com.seiderer.studyalarm.room.AppDatabase
import com.seiderer.studyalarm.room.DailyQuestionnaire
import com.seiderer.studyalarm.room.InitialQuestionnaire
import com.seiderer.studyalarm.room.MomentaryQuestionnaire
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import java.io.File
import java.io.FileOutputStream


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    @OptIn(ExperimentalSerializationApi::class)
    private fun <T> serializeToCSV(l : List<T>, filename: String, serializer : SerializationStrategy<T>) {
        val f = File(context?.getExternalFilesDir(null), filename)
        FileOutputStream(f).use { output ->
            var writeHeader = true
            for (q in l) {
                var str = CSVFormat("\t", "\n").encodeToString(serializer, q) + "\n"

                if (writeHeader) {
                    writeHeader = false
                } else {
                    str = str.lines()[1]    // remove header
                }

                output.write(str.toByteArray())
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnClear: Button = view.findViewById(R.id.btnClear)
        btnClear.setOnClickListener {

            val builder = AlertDialog.Builder(this.context)

            with(builder)
            {
                setTitle(R.string.dialogDeleteDataTitle)
                setMessage(R.string.dialogDeleteDataMsg)
                setPositiveButton(R.string.yes) { _, _ ->

                    // Documentation: "After deleting the rows, Room will set a WAL checkpoint and run VACUUM."
                    // -> data in database is really erased!
                    AppDatabase.getInstance(view.context).clearAllTables()

                    Toast.makeText(this.context, R.string.dialogDeleteDataDeleted, Toast.LENGTH_LONG).show()
                }
                setNegativeButton(R.string.no) { _, _ ->

                }
                show()
            }
        }


        val btnExport: Button = view.findViewById(R.id.btnExport)

        btnExport.setOnClickListener {

            //output data to private external directory

            AppDatabase.getInstance(view.context).initialQuestionnaireDao().let {
                serializeToCSV(it.getAll(), "exported_initial_questionnaire.csv", InitialQuestionnaire.serializer())
            }

            AppDatabase.getInstance(view.context).momentaryQuestionnaireDao().let {
                serializeToCSV(it.getAll(), "exported_momentary_questionnaire.csv", MomentaryQuestionnaire.serializer())
            }

            AppDatabase.getInstance(view.context).dailyQuestionnaireDao().let {
                serializeToCSV(it.getAll(), "exported_daily_questionnaire.csv", DailyQuestionnaire.serializer())
            }


            val builder = AlertDialog.Builder(this.context)

            with(builder)
            {
                setTitle(R.string.exportTitle)
                setMessage(context.getString(R.string.exportedTo) + " " + context?.getExternalFilesDir(null).toString())
                setPositiveButton(android.R.string.ok) { _, _ ->
                }
                show()
            }

        }

    }



}