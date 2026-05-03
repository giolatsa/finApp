package com.example.financeapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    
    private val myName = "გიორგი"
    private val myLastName = "ლაცაბიძე"
    private val myBirthMonth = 14 // სექტემბერი
    private val myBirthYear = 2004

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvResult = view.findViewById<TextView>(R.id.ab_tv_result_info)
        val tvIdentity = view.findViewById<TextView>(R.id.ab_tv_identity)

        // მონაცემების მიღება
        val model = arguments?.getSerializable("finance_data") as? FinanceModel

        if (model != null) {
            val financeManager = FinanceManager(myLastName, myBirthMonth)
            
            val isEnough = financeManager.isSalaryEnough(model)
            val savings = financeManager.calculateSavings(model)
            val remaining = financeManager.calculateRemaining(model)

            // ვიზუალური ვალიდაცია (ფერების ცვლა)
            if (!isEnough) {
                tvResult.setTextColor(Color.RED)
                tvResult.text = "ხარჯები აღემატება ხელფასს!\nდეფიციტი: ${remaining}"
            } else {
                tvResult.setTextColor(Color.parseColor("#4CAF50")) // მწვანე ფერი
                tvResult.text = "ხარჯები დაფარულია.\nდანაზოგი (${financeManager.savingsPercent}%): $savings\nდარჩენილი ბალანსი: $remaining"
            }
        }

        // Dynamic Identity კოდიდან
        tvIdentity.text = "შემქმნელი: $myName $myLastName\nდაბადების წელი: $myBirthYear"
    }
}
