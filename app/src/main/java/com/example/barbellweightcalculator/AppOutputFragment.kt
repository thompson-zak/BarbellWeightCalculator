package com.example.barbellweightcalculator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.barbellweightcalculator.databinding.AppOutputFragmentBinding
import kotlin.math.roundToInt

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AppOutputFragment : Fragment() {

    private var _binding: AppOutputFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val TAG = "AppOutputFragment"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = AppOutputFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var weight = 0
        var unit = "lbs"

        if(arguments != null) {
            val inputBundle = AppOutputFragmentArgs.fromBundle(requireArguments())
            weight = inputBundle.weight
            unit = inputBundle.unit

            val conversionNumber = 2.205
            //If number is given as lbs, divide by. If number is given as kgs, multiply by.
            val isPounds = unit.contains("lbs")
            val convertedWeight = if (isPounds) {
                (weight/conversionNumber).roundToInt()
            } else {
                (weight*conversionNumber).roundToInt()
            }
            val convertedUnit = if (isPounds) {
                "kgs"
            } else {
                "lbs"
            }

            val convertedWeightAndUnit : TextView = view.findViewById(R.id.converted_weight_and_unit) as TextView
            convertedWeightAndUnit.text = "$weight $unit --> $convertedWeight $convertedUnit"
            convertedWeightAndUnit.invalidate()
            convertedWeightAndUnit.requestLayout()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}