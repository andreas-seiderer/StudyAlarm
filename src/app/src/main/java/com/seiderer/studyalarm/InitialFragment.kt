package com.seiderer.studyalarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.seiderer.studyalarm.helpers.Helpers
import com.seiderer.studyalarm.room.AppDatabase
import com.seiderer.studyalarm.room.InitialQuestionnaire

class InitialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_initial, container, false)
    }


    private fun saveQuestions(view: View) : Boolean {
        val viewQAge: EditText = view.findViewById(R.id.editTextNumberAge)

        if (viewQAge.text.toString().isEmpty())
            return false

        val qAge = viewQAge.text.toString().toInt()

        // gender

        val viewQGender : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonGenderFemale) as RadioButton,
            view.findViewById(R.id.radioButtonGenderMale)  as RadioButton,
            view.findViewById(R.id.radioButtonGenderDiverse)  as RadioButton)

        val qGender: Int = Helpers.getCheckedRadiobuttonIndex(viewQGender)

        if (qGender == -1)
            return false

        // dominant hand

        val viewQDominantHand : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonDominantHandLeft) as RadioButton,
            view.findViewById(R.id.radioButtonDominantHandRight) as RadioButton,
            view.findViewById(R.id.radioButtonDominantHandBoth) as RadioButton)

        val qDominantHand: Int = Helpers.getCheckedRadiobuttonIndex(viewQDominantHand)

        if (qDominantHand == -1)
            return false

        // wrist wearable

        val viewQWristWearable : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonWristbandLeft) as RadioButton,
            view.findViewById(R.id.radioButtonWristbandRight)  as RadioButton,
            view.findViewById(R.id.radioButtonWristbandBoth)  as RadioButton)

        val qWristWearable: Int = Helpers.getCheckedRadiobuttonIndex(viewQWristWearable)

        if (qWristWearable == -1)
            return false

        // height
        val viewQHeight: EditText = view.findViewById(R.id.editTextNumberHeight)

        if (viewQHeight.text.toString().isEmpty())
            return false

        val qHeight = viewQHeight.text.toString().toInt()


        // weight
        val viewQWeight: EditText = view.findViewById(R.id.editTextNumberWeight)

        if (viewQWeight.text.toString().isEmpty())
            return false

        val qWeight = viewQWeight.text.toString().toInt()


        // profession
        val viewQProfession: EditText = view.findViewById(R.id.editTextProfession)

        if (viewQProfession.text.toString().isEmpty())
            return false

        val qProfession = viewQProfession.text.toString()


        // mindfulness techniques

        val viewQMindfulness : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonMindfulnessNo) as RadioButton,
            view.findViewById(R.id.radioButtonYesNoPractice)  as RadioButton,
            view.findViewById(R.id.radioButtonYesPractice)  as RadioButton)

        val qMindfulness: Int = Helpers.getCheckedRadiobuttonIndex(viewQMindfulness)

        if (qMindfulness == -1)
            return false


        // mindfulness interventions

        val viewQHelpMindfulnessInterventions : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonMindfulnessInterventionNo) as RadioButton,
            view.findViewById(R.id.radioButtonMindfulnessInterventionYes)  as RadioButton,
            view.findViewById(R.id.radioButtonMindfulnessInterventionYesStrongly)  as RadioButton)

        val qHelpMindfulnessInterventions: Int = Helpers.getCheckedRadiobuttonIndex(viewQHelpMindfulnessInterventions)

        if (qHelpMindfulnessInterventions == -1)
            return false


        // BFI_1

        val viewQBig501 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI1_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI1_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI1_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI1_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI1_5)  as RadioButton
        )

        val qBig501: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig501)

        if (qBig501 == -1)
            return false

        // BFI_2

        val viewQBig502 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI2_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI2_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI2_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI2_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI2_5)  as RadioButton
        )

        val qBig502: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig502)

        if (qBig502 == -1)
            return false

        // BFI_3

        val viewQBig503 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI3_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI3_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI3_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI3_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI3_5)  as RadioButton
        )

        val qBig503: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig503)

        if (qBig503 == -1)
            return false


        // BFI_4

        val viewQBig504 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI4_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI4_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI4_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI4_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI4_5)  as RadioButton
        )

        val qBig504: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig504)

        if (qBig504 == -1)
            return false


        // BFI_5

        val viewQBig505 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI5_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI5_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI5_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI5_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI5_5)  as RadioButton
        )

        val qBig505: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig505)

        if (qBig505 == -1)
            return false


        // BFI_6

        val viewQBig506 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI6_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI6_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI6_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI6_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI6_5)  as RadioButton
        )

        val qBig506: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig506)

        if (qBig506 == -1)
            return false


        // BFI_7

        val viewQBig507 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI7_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI7_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI7_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI7_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI7_5)  as RadioButton
        )

        val qBig507: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig507)

        if (qBig507 == -1)
            return false


        // BFI_8

        val viewQBig508 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI8_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI8_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI8_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI8_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI8_5)  as RadioButton
        )

        val qBig508: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig508)

        if (qBig508 == -1)
            return false


        // BFI_9

        val viewQBig509 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI9_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI9_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI9_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI9_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI9_5)  as RadioButton
        )

        val qBig509: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig509)

        if (qBig509 == -1)
            return false


        // BFI_10

        val viewQBig510 : Array<RadioButton> = arrayOf(
            view.findViewById(R.id.radioButtonBFI10_1) as RadioButton,
            view.findViewById(R.id.radioButtonBFI10_2)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI10_3)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI10_4)  as RadioButton,
            view.findViewById(R.id.radioButtonBFI10_5)  as RadioButton
        )

        val qBig510: Int = Helpers.getCheckedRadiobuttonIndex(viewQBig510)

        if (qBig510 == -1)
            return false


        // init Data class
        val initialQuestionnaireData = InitialQuestionnaire(
            0,
            System.currentTimeMillis(),
            qAge,
            qGender,
            qDominantHand,
            qWristWearable,
            qHeight,
            qWeight,
            qProfession,
            qMindfulness,
            qHelpMindfulnessInterventions,
            qBig501,
            qBig502,
            qBig503,
            qBig504,
            qBig505,
            qBig506,
            qBig507,
            qBig508,
            qBig509,
            qBig510
        )

        val initialQuestionnaireDao = AppDatabase.getInstance(view.context).initialQuestionnaireDao()

        //insert into database
        initialQuestionnaireDao.insertAll(initialQuestionnaireData)

        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSave: Button = view.findViewById(R.id.buttonInitialSave)
        btnSave.setOnClickListener {
            if (saveQuestions(view)) {
                Toast.makeText(this.context, R.string.saveQuestionnaireSuccess, Toast.LENGTH_LONG)
                    .show()

                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_initialFragment_to_startFragment)
            }
            else {
                Toast.makeText(this.context, R.string.saveQuestionnaireProblem, Toast.LENGTH_LONG).show()
            }
        }
    }
}