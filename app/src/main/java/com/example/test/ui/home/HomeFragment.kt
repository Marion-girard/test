package com.example.test.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.example.test.ui.home.HomeViewModel


import com.example.test.R
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.ui.shared.SharedViewModel

import java.util.*

class HomeFragment : Fragment() {

    private var events: MutableMap<String, String> = mutableMapOf()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel // Assurez-vous que DashboardViewModel est défini
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.text.observe(viewLifecycleOwner) { date ->
            binding.textHome.text = date
        }
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Observer le calendrier
        sharedViewModel.calendarEvent.observe(viewLifecycleOwner) { event ->
            val (date, emojiResId) = event
            updateCalendarDay(date, emojiResId)

        }
        return binding.root
    }
    private fun updateCalendarDay(date: String, emojiResId: Int) {
        // Convertir la date en format que le calendrier peut utiliser
        val parts = date.split("-")
        val day = parts[0].toInt()
        val month = parts[1].toInt() - 1 // Les mois sont 0-indexés
        val year = parts[2].toInt()

        // Créer un objet CalendarDay
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val calendarDay = CalendarDay(calendar)

        // Mettre à jour l'image de ressource pour ce jour
        calendarDay.imageResource = emojiResId
        events[date] = "Événement pour $date" // Ajoutez une description si nécessaire

        // Mettre à jour le calendrier
        binding.calendar.setCalendarDays(listOf(calendarDay))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarView: CalendarView = binding.calendar

        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format("%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format("%02d", calendarDay.calendar.get(Calendar.MONTH) + 1) // Ajoutez 1 car les mois sont 0-indexés
                val year = calendarDay.calendar.get(Calendar.YEAR)
                val selectedDate = "$day-$month-$year" // Créez la chaîne de date

                // Mettez à jour la date dans le ViewModel
                homeViewModel.updateDate(selectedDate)

                // Afficher un Toast pour l'événement
                if (events.containsKey(selectedDate)) {
                    Toast.makeText(requireContext(), events[selectedDate], Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Nothing to do", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}