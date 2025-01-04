package com.example.test.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.databinding.FragmentDashboardBinding
import com.example.test.ui.data.Mood
import com.example.test.ui.data.MoodDao
import com.example.test.ui.moodInsert.MoodApplication
import com.example.test.ui.moodInsert.MoodViewModel
import com.example.test.ui.shared.SharedViewModel

import com.example.test.ui.moodInsert.MoodViewModelFactory

class DashboardFragment: Fragment() {

    private val viewModel: MoodViewModel by activityViewModels {

        MoodViewModelFactory(
            (activity?.application as MoodApplication).database
                .moodDao()
        )
    }
    lateinit var mood: Mood
    private var _binding: FragmentDashboardBinding? = null
    private lateinit var sharedViewModel: SharedViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = "22-11-2024" // Remplacez par la date actuelle que vous souhaitez utiliser
        val moodVeryDissatisfied = R.drawable.baseline_sentiment_very_dissatisfied_24
        val moodNeutral = R.drawable.baseline_sentiment_neutral_24
        val moodVerySatisfied =  R.drawable.baseline_sentiment_very_satisfied_24
        val moodSatisfied = R.drawable.baseline_sentiment_satisfied_alt_24
        val moodDisatisfied = R.drawable.baseline_sentiment_dissatisfied_24
        binding.imageButton5.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate,moodVeryDissatisfied) // Remplacez par votre drawable

            saveMood(currentDate, moodVeryDissatisfied)

            Toast.makeText(requireContext(), "pas heureux du tous", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton2.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, moodNeutral) // Remplacez par votre drawable
            saveMood(currentDate, moodNeutral)
            Toast.makeText(requireContext(), "neutre", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton1.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, moodVerySatisfied) // Remplacez par votre drawable
            saveMood(currentDate, moodVerySatisfied)
            Toast.makeText(requireContext(), "tres heureux", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, moodSatisfied) // Remplacez par votre drawable
            saveMood(currentDate, moodSatisfied)
            Toast.makeText(requireContext(), "heureux", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton4.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, moodDisatisfied) // Remplacez par votre drawable
           saveMood(currentDate, moodDisatisfied)
            Toast.makeText(requireContext(), "pas bien du tous", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveMood(date: String, mood: Int) {
        // Créez un nouvel objet Mood avec la date et l'humeur
        val newMood = Mood(date = date, mood = mood)

        // Utilisez le ViewModel pour insérer l'humeur dans la base de données
        viewModel.insertItem(newMood)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libérez le binding pour éviter les fuites de mémoire
    }
}