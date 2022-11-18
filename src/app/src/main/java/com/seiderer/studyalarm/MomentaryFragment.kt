package com.seiderer.studyalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.seiderer.studyalarm.helpers.Helpers
import com.seiderer.studyalarm.room.AppDatabase
import com.seiderer.studyalarm.room.MomentaryQuestionnaire

class MomentaryFragment : Fragment(), OnClickListener {

    private lateinit var radioButtonMoodGroup : Array<RadioButton>  // check modal selection!
    private lateinit var radioButtonStressedGroup : Array<RadioButton> // check modal selection!
    private lateinit var radioButtonPhysicalActivityGroup : Array<RadioButton>
    private lateinit var radioButtonCurrentLocationGroup : Array<RadioButton>  // check modal selection!
    private lateinit var radioButtonPeopleGroup : Array<RadioButton>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_momentary, container, false)
    }


    private fun saveQuestions(view: View) : Boolean {

        val qMood: Int = Helpers.getCheckedRadiobuttonIndex(radioButtonMoodGroup)

        if (qMood == -1)
            return false


        val qStressed: Int = Helpers.getCheckedRadiobuttonIndex(radioButtonStressedGroup)

        if (qStressed == -1)
            return false


        val qPhysicalActivity: Int = Helpers.getCheckedRadiobuttonIndex(radioButtonPhysicalActivityGroup)

        if (qPhysicalActivity == -1)
            return false


        val viewQDominantActivity: EditText = view.findViewById(R.id.editTextTextDominantActivity)

        if (viewQDominantActivity.text.toString().isEmpty())
            return false

        val qDominantActivity = viewQDominantActivity.text.toString()


        val qLocation: Int = Helpers.getCheckedRadiobuttonIndex(radioButtonCurrentLocationGroup)

        if (qLocation == -1)
            return false


        val qPersons: Int = Helpers.getCheckedRadiobuttonIndex(radioButtonPeopleGroup)

        if (qPersons == -1)
            return false


        // init Data class
        val momentaryQuestionnaireData = MomentaryQuestionnaire(
            0,
            System.currentTimeMillis(),
            qMood,
            qStressed,
            qPhysicalActivity,
            qDominantActivity,
            qLocation,
            qPersons
        )

        val momentaryQuestionnaireDao = AppDatabase.getInstance(view.context).momentaryQuestionnaireDao()

        momentaryQuestionnaireDao.insertAll(momentaryQuestionnaireData)

        return true
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioButtonMoodGroup = arrayOf(
            view.findViewById(R.id.radioButtonMood1) as RadioButton,
            view.findViewById(R.id.radioButtonMood2) as RadioButton,
            view.findViewById(R.id.radioButtonMood3) as RadioButton,
            view.findViewById(R.id.radioButtonMood4) as RadioButton,
            view.findViewById(R.id.radioButtonMood5) as RadioButton
        )

        for (r in radioButtonMoodGroup) {
            r.setOnClickListener(this)
        }

        radioButtonStressedGroup = arrayOf(
            view.findViewById(R.id.radioButtonStressed1) as RadioButton,
            view.findViewById(R.id.radioButtonStressed2) as RadioButton,
            view.findViewById(R.id.radioButtonStressed3) as RadioButton,
            view.findViewById(R.id.radioButtonStressed4) as RadioButton,
            view.findViewById(R.id.radioButtonStressed5) as RadioButton
        )

        for (r in radioButtonStressedGroup) {
            r.setOnClickListener(this)
        }

        radioButtonPhysicalActivityGroup = arrayOf(
            view.findViewById(R.id.radioButtonPhysicalActivityLow) as RadioButton,
            view.findViewById(R.id.radioButtonPhysicalActivityMedium) as RadioButton,
            view.findViewById(R.id.radioButtonPhysicalActivityHigh) as RadioButton,
        )

        radioButtonCurrentLocationGroup = arrayOf(
            view.findViewById(R.id.radioButtonCurrentLocation1) as RadioButton,
            view.findViewById(R.id.radioButtonCurrentLocation2) as RadioButton,
            view.findViewById(R.id.radioButtonCurrentLocation3) as RadioButton,
            view.findViewById(R.id.radioButtonCurrentLocation4) as RadioButton
        )

        for (r in radioButtonCurrentLocationGroup) {
            r.setOnClickListener(this)
        }

        radioButtonPeopleGroup = arrayOf(
            view.findViewById(R.id.radioButtonPeople1) as RadioButton,
            view.findViewById(R.id.radioButtonPeople2) as RadioButton,
            view.findViewById(R.id.radioButtonPeople3) as RadioButton,
        )

        val btnSave: Button = view.findViewById(R.id.buttonMomentarySave)
        btnSave.setOnClickListener {
            if (saveQuestions(view)) {
                Toast.makeText(this.context, R.string.saveQuestionnaireSuccess, Toast.LENGTH_LONG)
                    .show()

                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_momentaryFragment_to_startFragment)
            }
            else {
                Toast.makeText(this.context, R.string.saveQuestionnaireProblem, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(v: View?) {
        // allow just one RadioButton to be checked (required for RadioButtons in Layouts)
        Helpers.justAllowOneCheckedRadiobutton(radioButtonMoodGroup, v?.id)
        Helpers.justAllowOneCheckedRadiobutton(radioButtonStressedGroup, v?.id)
        Helpers.justAllowOneCheckedRadiobutton(radioButtonCurrentLocationGroup, v?.id)
    }
}

