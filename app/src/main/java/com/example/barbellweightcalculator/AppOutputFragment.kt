package com.example.barbellweightcalculator

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
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

            // Barbell list is an ordered list of plate amounts to be used 25kg -> 1.25kg
            val barbellList : ArrayList<Int> = ArrayList()
            val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("Barbell", 0)
            // Initially remove weight of bar from converted weight, then halve it since this is per side
            var tempWeight = (convertedWeight - 20)/2.0

            if (sharedPreferences.getBoolean("25kg", true)) {
                barbellList.add(floor(tempWeight/25.0).toInt())
                tempWeight %= 25
            } else {
                barbellList.add(0)
            }

            if (sharedPreferences.getBoolean("20kg", true)) {
                barbellList.add(floor(tempWeight/20.0).toInt())
                tempWeight %= 20
            } else {
                barbellList.add(0)
            }

            if (sharedPreferences.getBoolean("15kg", true)) {
                barbellList.add(floor(tempWeight/15.0).toInt())
                tempWeight %= 15
            } else {
                barbellList.add(0)
            }

            if (sharedPreferences.getBoolean("10kg", true)) {
                barbellList.add(floor(tempWeight/10.0).toInt())
                tempWeight %= 10
            } else {
                barbellList.add(0)
            }

            if (sharedPreferences.getBoolean("5kg", true)) {
                barbellList.add(floor(tempWeight/5.0).toInt())
                tempWeight %= 5
            } else {
                barbellList.add(0)
            }

            if (sharedPreferences.getBoolean("2_5kg", true)) {
                barbellList.add(floor(tempWeight/2.5).toInt())
                tempWeight %= 2.5
            } else {
                barbellList.add(0)
            }

            if (sharedPreferences.getBoolean("1_2_5kg", true)) {
                barbellList.add(floor(tempWeight/1.25).toInt())
                tempWeight %= 1.25
            } else {
                barbellList.add(0)
            }

            Log.v("AppOutput", barbellList.toString())

            var plateIndex = 1
            for(i in 0..6) {
                val numPlates = barbellList[i]
                val plateColor = getPlateColor(i)
                val plateHeight = getPlateHeight(i)
                //val layoutParams = ConstraintLayout.LayoutParams(8, plateHeight)
                // Plates will only get smaller than max 125, so take the difference and add to default 68 margin
                //layoutParams.topMargin = 68 + (125-plateHeight)
                for(j in plateIndex until (plateIndex+numPlates)) {
                    val plateId = "plate_$j"
                    val plateViewId = resources.getIdentifier(plateId, "id", requireContext().packageName)
                    val plateView = view.findViewById(plateViewId) as View

                    requireActivity().runOnUiThread {
                        //plateView.layoutParams = layoutParams
                        plateView.setBackgroundColor(Color.parseColor(plateColor))
                        plateView.visibility = View.VISIBLE
                        plateView.postInvalidate()
                    }
                }
                plateIndex += numPlates
            }
        }
    }

    // Returns expected color based on plate list index
    private fun getPlateColor(plateIndex: Int) : String {
        return when (plateIndex) {
            0 -> "#FF0000" //25kg
            1 -> "#4682B4" //20kg
            2 -> "#FFC300" //15kg
            3 -> "#008000" //10kg
            4 -> "#000000" //5kg
            5 -> "#000000" //2.5kg
            6 -> "#000000" //1.25kg
            else -> {
                "#FF0000"
            }
        }
    }

    // Returns expected height based on plate list index
    private fun getPlateHeight(plateIndex: Int) : Int {
        return when (plateIndex) {
            0 -> 125 //25kg
            1 -> 125 //20kg
            2 -> 100 //15kg
            3 -> 75  //10kg
            4 -> 45  //5kg
            5 -> 30  //2.5kg
            6 -> 15  //1.25kg
            else -> {
                125
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}