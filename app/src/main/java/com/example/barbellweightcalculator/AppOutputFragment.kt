package com.example.barbellweightcalculator

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.barbellweightcalculator.databinding.AppOutputFragmentBinding
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AppOutputFragment : Fragment() {

    private var _binding: AppOutputFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = AppOutputFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val inputBundle = AppOutputFragmentArgs.fromBundle(requireArguments())
            val weight = inputBundle.weight
            val unit = inputBundle.unit

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

            val barbellMap : HashMap<String, Int> = HashMap()
            val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("Barbell", 0)
            // Initially remove weight of bar from converted weight, then halve it since this is per side
            var tempWeight = (convertedWeight - 20)/2.0
            if (sharedPreferences.getBoolean("25kg", true)) {
                barbellMap.put("25kg", floor(tempWeight/25.0).toInt())
                tempWeight %= 25
            }
            if (sharedPreferences.getBoolean("20kg", true)) {
                barbellMap.put("20kg", floor(tempWeight/20.0).toInt())
                tempWeight %= 20
            }
            if (sharedPreferences.getBoolean("15kg", true)) {
                barbellMap.put("15kg", floor(tempWeight/15.0).toInt())
                tempWeight %= 15
            }
            if (sharedPreferences.getBoolean("10kg", true)) {
                barbellMap.put("10kg", floor(tempWeight/10.0).toInt())
                tempWeight %= 10
            }
            if (sharedPreferences.getBoolean("5kg", true)) {
                barbellMap.put("5kg", floor(tempWeight/5.0).toInt())
                tempWeight %= 5
            }
            if (sharedPreferences.getBoolean("2_5kg", true)) {
                barbellMap.put("2_5kg", floor(tempWeight/2.5).toInt())
                tempWeight %= 2.5
            }
            if (sharedPreferences.getBoolean("1_2_5kg", true)) {
                barbellMap.put("1_2_5kg", floor(tempWeight/1.25).toInt())
                tempWeight %= 1.25
            }

            Log.v("AppOutput", barbellMap.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}