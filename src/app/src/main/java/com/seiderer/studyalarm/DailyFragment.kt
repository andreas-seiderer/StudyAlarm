package com.seiderer.studyalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.seiderer.studyalarm.helpers.Helpers
import com.seiderer.studyalarm.room.AppDatabase
import com.seiderer.studyalarm.room.DailyQuestionnaire

class DailyFragment : Fragment(), View.OnClickListener {

    private lateinit var radioButtonSQSGroup : Array<RadioButton>   // check modal selection!
    private lateinit var radioButtonBDSST1Group : Array<RadioButton>
    private lateinit var radioButtonBDSST2Group : Array<RadioButton>
    private lateinit var radioButtonBDSST3Group : Array<RadioButton>
    private lateinit var radioButtonBDSST4Group : Array<RadioButton>
    private lateinit var radioButtonBDSST5Group : Array<RadioButton>
    private lateinit var radioButtonBDSST6Group : Array<RadioButton>
    private lateinit var radioButtonBDSST7Group : Array<RadioButton>
    private lateinit var radioButtonBDSST8Group : Array<RadioButton>
    private lateinit var radioButtonBDSST9Group : Array<RadioButton>
    private lateinit var radioButtonBDSST10Group : Array<RadioButton>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }


    private fun saveQuestions(view: View) : Boolean {
        val qSleepQuality : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonSQSGroup)

        if (qSleepQuality == -1)
            return false


        val qBDSST01 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST1Group)

        if (qBDSST01 == -1)
            return false

        val qBDSST02 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST2Group)

        if (qBDSST02 == -1)
            return false

        val qBDSST03 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST3Group)

        if (qBDSST03 == -1)
            return false

        val qBDSST04 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST4Group)

        if (qBDSST04 == -1)
            return false

        val qBDSST05 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST5Group)

        if (qBDSST05 == -1)
            return false

        val qBDSST06 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST6Group)

        if (qBDSST06 == -1)
            return false

        val qBDSST07 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST7Group)

        if (qBDSST07 == -1)
            return false

        val qBDSST08 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST8Group)

        if (qBDSST08 == -1)
            return false

        val qBDSST09 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST9Group)

        if (qBDSST09 == -1)
            return false

        val qBDSST10 : Int = Helpers.getCheckedRadiobuttonIndex(radioButtonBDSST10Group)

        if (qBDSST10 == -1)
            return false

        val dailyQuestionnaireData = DailyQuestionnaire(
            0,
            System.currentTimeMillis(),
            qSleepQuality,
            qBDSST01,
            qBDSST02,
            qBDSST03,
            qBDSST04,
            qBDSST05,
            qBDSST06,
            qBDSST07,
            qBDSST08,
            qBDSST09,
            qBDSST10
        )

        val dailyQuestionnaireDao = AppDatabase.getInstance(view.context).dailyQuestionnaireDao()

        dailyQuestionnaireDao.insertAll(dailyQuestionnaireData)

        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioButtonSQSGroup = arrayOf(
            view.findViewById(R.id.radioButtonSQS1) as RadioButton,
            view.findViewById(R.id.radioButtonSQS2) as RadioButton,
            view.findViewById(R.id.radioButtonSQS3) as RadioButton,
            view.findViewById(R.id.radioButtonSQS4) as RadioButton,
            view.findViewById(R.id.radioButtonSQS5) as RadioButton
        )

        for (r in radioButtonSQSGroup) {
            r.setOnClickListener(this)
        }


        radioButtonBDSST1Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST1_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST1_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST1_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST1_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST1_5) as RadioButton
        )

        radioButtonBDSST2Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST2_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST2_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST2_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST2_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST2_5) as RadioButton
        )

        radioButtonBDSST3Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST3_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST3_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST3_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST3_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST3_5) as RadioButton
        )

        radioButtonBDSST4Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST4_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST4_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST4_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST4_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST4_5) as RadioButton
        )

        radioButtonBDSST5Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST5_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST5_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST5_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST5_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST5_5) as RadioButton
        )

        radioButtonBDSST6Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST6_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST6_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST6_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST6_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST6_5) as RadioButton
        )

        radioButtonBDSST7Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST7_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST7_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST7_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST7_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST7_5) as RadioButton
        )

        radioButtonBDSST8Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST8_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST8_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST8_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST8_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST8_5) as RadioButton
        )

        radioButtonBDSST9Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST9_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST9_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST9_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST9_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST9_5) as RadioButton
        )

        radioButtonBDSST10Group = arrayOf(
            view.findViewById(R.id.radioButtonBDSST10_1) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST10_2) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST10_3) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST10_4) as RadioButton,
            view.findViewById(R.id.radioButtonBDSST10_5) as RadioButton
        )

        val btnSave: Button = view.findViewById(R.id.buttonDailySave)
        btnSave.setOnClickListener {
            if (saveQuestions(view)) {
                Toast.makeText(this.context, R.string.saveQuestionnaireSuccess, Toast.LENGTH_LONG)
                    .show()

                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_dailyFragment_to_startFragment)
            }
            else {
                Toast.makeText(this.context, R.string.saveQuestionnaireProblem, Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onClick(v: View?) {
        // allow just one RadioButton to be checked (required for RadioButtons in Layouts)
        Helpers.justAllowOneCheckedRadiobutton(radioButtonSQSGroup, v?.id)
    }
}