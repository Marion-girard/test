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
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.databinding.FragmentDashboardBinding
import com.example.test.ui.shared.SharedViewModel

class DashboardFragment : Fragment() {

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

        val currentDate = "21-11-2024" // Remplacez par la date actuelle que vous souhaitez utiliser

        binding.imageButton5.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, R.drawable.baseline_sentiment_very_dissatisfied_24) // Remplacez par votre drawable

            Toast.makeText(requireContext(), "pas heureux du tous", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton2.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, R.drawable.baseline_sentiment_neutral_24) // Remplacez par votre drawable

            Toast.makeText(requireContext(), "neutre", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton1.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, R.drawable.baseline_sentiment_very_satisfied_24) // Remplacez par votre drawable

            Toast.makeText(requireContext(), "tres heureux", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, R.drawable.baseline_sentiment_satisfied_alt_24) // Remplacez par votre drawable

            Toast.makeText(requireContext(), "heureux", Toast.LENGTH_SHORT).show()
        }

        binding.imageButton4.setOnClickListener {
            sharedViewModel.updateCalendarEvent(currentDate, R.drawable.baseline_sentiment_dissatisfied_24) // Remplacez par votre drawable

            Toast.makeText(requireContext(), "pas bien du tous", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libérez le binding pour éviter les fuites de mémoire
    }
}