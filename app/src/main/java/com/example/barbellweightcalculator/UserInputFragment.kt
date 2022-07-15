package com.example.barbellweightcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.barbellweightcalculator.databinding.UserInputFragmentBinding


/**
 * A [Fragment] that takes a weight and type as input
 * Then converts it and navigates to the display [Fragment] for the conversion
 */
class UserInputFragment : Fragment() {

    private var _binding: UserInputFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.user_input_fragment, container, false)
        val radioGroup = view.findViewById(R.id.weight_selection_radio_original) as RadioGroup
        val conversionLabel = view.findViewById(R.id.conversion_label) as TextView
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById(checkedId) as RadioButton
            val rbText = rb.text as String
            // If user selects kg as input, notify them they will be converting to lbs - as well as the reverse
            if(rbText.contains("kg"))
                conversionLabel.setText(R.string.converting_to_lbs)
            else
                conversionLabel.setText(R.string.converting_to_kgs)
        }

        _binding = UserInputFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            // Get weight integer and unit then pass to next fragment as args
            val conversionWeightTextView = view.findViewById(R.id.input_weight_form) as TextView
            val conversionWeight = Integer.parseInt(conversionWeightTextView.text.toString())

            val radioGroup = view.findViewById(R.id.weight_selection_radio_original) as RadioGroup
            val selectedRadioBtn = view.findViewById(radioGroup.checkedRadioButtonId) as RadioButton
            val unitOfWeight = selectedRadioBtn.text.toString()

            val bundle = Bundle()
            bundle.putInt("weight", conversionWeight)
            bundle.putString("unit", unitOfWeight)
            findNavController().navigate(R.id.action_UserInput_to_AppOutput, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}