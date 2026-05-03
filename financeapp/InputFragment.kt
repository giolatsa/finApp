package com.example.financeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editSalary = view.findViewById<EditText>(R.id.ab_edit_salary)
        val editRent = view.findViewById<EditText>(R.id.ab_edit_rent)
        val editFood = view.findViewById<EditText>(R.id.ab_edit_food)
        val btnCalculate = view.findViewById<Button>(R.id.ab_btn_calculate)

        btnCalculate.setOnClickListener {
            val salary = editSalary.text.toString().toDoubleOrNull() ?: 0.0
            val rent = editRent.text.toString().toDoubleOrNull() ?: 0.0
            val food = editFood.text.toString().toDoubleOrNull() ?: 0.0

            if (salary > 0) {
                val model = FinanceModel(salary, rent, food)
                
                // მონაცემების გადაცემა Bundle-ით
                val bundle = Bundle().apply {
                    putSerializable("finance_data", model)
                }
                
                val resultFragment = ResultFragment().apply {
                    arguments = bundle
                }

                // Fragment-ების ჩანაცვლება
                parentFragmentManager.beginTransaction()
                    .replace(R.id.ab_fragment_container, resultFragment)
                    .addToBackStack(null) // უკან დაბრუნების შესაძლებლობა
                    .commit()
            } else {
                Toast.makeText(requireContext(), "შეიყვანეთ ხელფასი", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
