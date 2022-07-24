package com.example.barbellweightcalculator

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.example.barbellweightcalculator.databinding.SettingsFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set initial checkbox values based on saved local data
        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("Barbell", 0)
        view.findViewById<CheckBox>(R.id.checkBox25kg).setChecked(sharedPreferences.getBoolean("25kg", true))
        view.findViewById<CheckBox>(R.id.checkBox20kg).setChecked(sharedPreferences.getBoolean("20kg", true))
        view.findViewById<CheckBox>(R.id.checkBox15kg).setChecked(sharedPreferences.getBoolean("15kg", true))
        view.findViewById<CheckBox>(R.id.checkBox10kg).setChecked(sharedPreferences.getBoolean("10kg", true))
        view.findViewById<CheckBox>(R.id.checkBox5kg).setChecked(sharedPreferences.getBoolean("5kg", true))
        view.findViewById<CheckBox>(R.id.checkBox2_5kg).setChecked(sharedPreferences.getBoolean("2_5kg", true))
        view.findViewById<CheckBox>(R.id.checkBox1_2_5kg).setChecked(sharedPreferences.getBoolean("1_2_5kg", true))

        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        view.findViewById<CheckBox>(R.id.checkBox25kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("25kg", isChecked)
            editor.commit()
        }
        view.findViewById<CheckBox>(R.id.checkBox20kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("20kg", isChecked)
            editor.commit()
        }
        view.findViewById<CheckBox>(R.id.checkBox15kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("15kg", isChecked)
            editor.commit()
        }
        view.findViewById<CheckBox>(R.id.checkBox10kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("10kg", isChecked)
            editor.commit()
        }
        view.findViewById<CheckBox>(R.id.checkBox5kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("5kg", isChecked)
            editor.commit()
        }
        view.findViewById<CheckBox>(R.id.checkBox2_5kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("2_5kg", isChecked)
            editor.commit()
        }
        view.findViewById<CheckBox>(R.id.checkBox1_2_5kg).setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("1_2_5kg", isChecked)
            editor.commit()
        }
    }
}