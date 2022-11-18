package com.seiderer.studyalarm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.navigation.fragment.NavHostFragment
import com.seiderer.studyalarm.room.AppDatabase

class StartFragment : Fragment() {
    var handler: Handler = Handler(Looper.getMainLooper())
    var runnable: Runnable? = null
    var delay = 10000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSettings: Button = view.findViewById(R.id.btnSettings)
        btnSettings.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_startFragment_to_settingsFragment)
        }

        val btnInitial: Button = view.findViewById(R.id.btnInitialQuestions)
        btnInitial.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_startFragment_to_initialFragment)
        }

        val btnDaily: Button = view.findViewById(R.id.btnDailyQuestions)
        btnDaily.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_startFragment_to_dailyFragment)
        }

        val btnMomentary: Button = view.findViewById(R.id.btnMomentaryQuestions)
        btnMomentary.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_startFragment_to_momentaryFragment)
        }


        // daily questionnaire already done
        val dailyQuestionnaireDao = AppDatabase.getInstance(view.context).dailyQuestionnaireDao()
        val mostRecent = dailyQuestionnaireDao.mostRecentEntry()

        val checkBoxDaily: CheckBox = view.findViewById(R.id.checkBoxDaily)

        if (mostRecent != null)
            checkBoxDaily.isChecked = DateUtils.isToday(mostRecent.date)


        // initial questionnaire already done
        val initialQuestionnaireDao = AppDatabase.getInstance(view.context).initialQuestionnaireDao()
        val mostRecentInitial = initialQuestionnaireDao.mostRecentEntry()

        val checkBoxInitial: CheckBox = view.findViewById(R.id.checkBoxInitial)

        checkBoxInitial.isChecked = mostRecentInitial != null
        btnInitial.isEnabled = mostRecentInitial == null

    }

    override fun onResume() {

        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())

            view?.let {
                Log.d("StartFragment", "update")

                val dailyQuestionnaireDao = AppDatabase.getInstance(it.context).dailyQuestionnaireDao()
                val mostRecent = dailyQuestionnaireDao.mostRecentEntry()
                val checkBoxDaily: CheckBox = it.findViewById(R.id.checkBoxDaily)

                if (mostRecent != null)
                    checkBoxDaily.isChecked = DateUtils.isToday(mostRecent.date)
            }

        }.also { runnable = it }, delay.toLong())

        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable!!)
    }
}